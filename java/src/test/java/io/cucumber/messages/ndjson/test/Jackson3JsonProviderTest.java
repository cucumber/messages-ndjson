package io.cucumber.messages.ndjson.test;

import io.cucumber.messages.ndjson.Deserializer;
import io.cucumber.messages.ndjson.Serializer;
import io.cucumber.messages.ndjson.test.ClassLoaderUtil.FilteredClassLoader;
import io.cucumber.messages.types.Envelope;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static io.cucumber.messages.ndjson.test.ClassLoaderUtil.executeWith;
import static org.assertj.core.api.Assertions.assertThat;

final class Jackson3JsonProviderTest {

    @Test
    void testSerializer() {
        assertThat(loadDeserializer()).isNotEmpty();
    }

    @Test
    void testSerializerWithoutDependency() {
        var filteredClassLoader = new FilteredClassLoader("tools.jackson.");
        executeWith(filteredClassLoader, () -> assertThat(loadSerializer()).isEmpty());
    }

    @Test
    void testDeserializer() {
        assertThat(loadSerializer()).isNotEmpty();
    }

    @Test
    void testDeserializerWithoutDependency() {
        var filteredClassLoader = new FilteredClassLoader("tools.jackson.");
        executeWith(filteredClassLoader, () -> assertThat(loadSerializer()).isEmpty());
    }

    private static Optional<Serializer<Envelope>> loadDeserializer() {
        return JsonProviderUtil.instance("Jackson3")
                .map(json -> json.serializer(Envelope.class))
                .findFirst();
    }

    private static Optional<Deserializer<Envelope>> loadSerializer() {
        return JsonProviderUtil.instance("Jackson3")
                .map(json -> json.deserializer(Envelope.class))
                .findFirst();
    }
}
