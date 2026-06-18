package io.cucumber.messages.ndjson.test;

import io.cucumber.messages.MessageToNdjsonWriter;
import io.cucumber.messages.NdjsonToMessageReader;
import io.cucumber.messages.ndjson.Deserializer;
import io.cucumber.messages.ndjson.JsonFactory;
import io.cucumber.messages.ndjson.Serializer;
import io.cucumber.messages.types.Envelope;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedClass;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.file.Files.newInputStream;
import static java.nio.file.Files.newOutputStream;
import static org.assertj.core.api.Assertions.assertThat;

@ParameterizedClass
@MethodSource("services")
public class AcceptanceTest {

    private final Deserializer<Envelope> deserializer;
    private final Serializer<Envelope> serializer;

    public AcceptanceTest(Deserializer<Envelope> deserializer, Serializer<Envelope> serializer) {
        this.deserializer = deserializer;
        this.serializer = serializer;
    }

    static Stream<Arguments> services() {
        return ServiceLoader.load(JsonFactory.class).stream()
                .map(ServiceLoader.Provider::get)
                .map(jsonProvider -> Arguments.argumentSet(
                        jsonProvider.name(),
                        jsonProvider.deserializer(Envelope.class),
                           jsonProvider.serializer(Envelope.class)));
    }

    static List<TestCase> testCases() throws IOException {
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
    @MethodSource("testCases")
    void test(TestCase testCase) throws IOException {
        List<Envelope> expectedMessages = readMessages(testCase.source);
        // Tests a object -> file -> object round trip.
        Path tmp = out.resolve(testCase.name + ".ndjson");
        writeMessages(tmp, expectedMessages);
        List<Envelope> actualMessages = readMessages(tmp);
        assertThat(actualMessages).isEqualTo(expectedMessages);
    }

    private void writeMessages(Path resolved, List<Envelope> messages) throws IOException {
        try (MessageToNdjsonWriter writer = new MessageToNdjsonWriter(newOutputStream(resolved), serializer::writeValue)) {
            for (Envelope envelope : messages) {
                writer.write(envelope);
            }
        }
    }

    private List<Envelope> readMessages(Path testCase) throws IOException {
        try (var reader = new NdjsonToMessageReader(newInputStream(testCase), deserializer::readValue)) {
            return reader.lines().toList();
        }
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
