package io.cucumber.messages.ndjson;

import io.cucumber.messages.MessageToNdjsonWriter;
import io.cucumber.messages.NdjsonToMessageIterable;
import io.cucumber.messages.types.Envelope;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.file.Files.newInputStream;
import static java.nio.file.Files.newOutputStream;
import static org.assertj.core.api.Assertions.assertThat;

public class AcceptanceTest {

    private static final Deserializer deserializer = new Deserializer();
    private static final Serializer serializer = new Serializer();

    static List<TestCase> acceptance() throws IOException {
        List<Path> sources = getSources();
        List<TestCase> testCases = new ArrayList<>();
        sources.forEach(path -> testCases.add(new TestCase(path)));
        return testCases;
    }

    private static List<Path> getSources() throws IOException {
        try (Stream<Path> paths = Files.list(Paths.get("..", "testdata", "src"))) {
            return paths
                    .filter(path -> path.getFileName().toString().endsWith(".ndjson"))
                    .collect(Collectors.toList());
        }
    }

    @TempDir
    Path out;

    @ParameterizedTest
    @MethodSource("acceptance")
    void test(TestCase testCase) throws IOException {
        List<Envelope> expectedMessages = readMessages(testCase.source);
        // Tests a object -> file -> object round trip.
        Path tmp = out.resolve(testCase.name + ".ndjson");
        writeMessages(tmp, expectedMessages);
        List<Envelope> actualMessages = readMessages(tmp);
        assertThat(actualMessages).isEqualTo(expectedMessages);
    }

    private static void writeMessages(Path resolved, List<Envelope> messages) throws IOException {
        try (MessageToNdjsonWriter writer = new MessageToNdjsonWriter(newOutputStream(resolved), serializer)) {
            for (Envelope envelope : messages) {
                writer.write(envelope);
            }
        }
    }

    private static List<Envelope> readMessages(Path testCase) throws IOException {
        List<Envelope> envelopes = new ArrayList<>();
        try (NdjsonToMessageIterable reader = new NdjsonToMessageIterable(newInputStream(testCase), deserializer)) {
            reader.iterator().forEachRemaining(envelopes::add);
        }
        return envelopes;
    }

    static class TestCase {
        private final Path source;
        private final String name;

        TestCase(Path source) {
            this.source = source;
            String fileName = source.getFileName().toString();
            this.name = fileName.substring(0, fileName.lastIndexOf(".ndjson"));
        }

        @Override
        public String toString() {
            return name;
        }

    }

}
