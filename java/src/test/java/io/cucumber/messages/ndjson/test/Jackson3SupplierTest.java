package io.cucumber.messages.ndjson.test;

import io.cucumber.messages.ndjson.Deserializer;
import io.cucumber.messages.ndjson.Jackson3;
import io.cucumber.messages.ndjson.JsonFactory;
import io.cucumber.messages.ndjson.Serializer;
import io.cucumber.messages.types.Envelope;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.ServiceLoader;

import static io.cucumber.messages.ndjson.test.ClassLoaderUtil.executeWith;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Jackson3SupplierTest {

    @Test
    void testSerializer() {
        assertThat(loadDeserializer())
                .map(Object::getClass)
                .map(Class::getName)
                .contains("io.cucumber.messages.ndjson.Jackson3Json");
    }


    @Test
    void testDeserializer() {
        assertThat(loadSerializer())
                .map(Object::getClass)
                .map(Class::getName)
                .contains("io.cucumber.messages.ndjson.Jackson3Json");
    }

    @Test
    void testDeserializerWithoutDependency() {
        var filteredClassLoader = new FilteredClassLoader("tools.jackson.");
        executeWith(filteredClassLoader, () -> assertThat(loadSerializer()).isEmpty());
    }

    private static Optional<Serializer<Envelope>> loadDeserializer() {
        var load = ServiceLoader.load(JsonFactory.class);
        return load.stream()
                .map(ServiceLoader.Provider::get)
                .filter(Jackson3.class::isInstance)
                .map(json -> json.serializer(Envelope.class))
                .findFirst();
    }

    private static Optional<Deserializer<Envelope>> loadSerializer() {
        var load = ServiceLoader.load(JsonFactory.class);
        return load.stream()
                .map(ServiceLoader.Provider::get)
                .filter(Jackson3.class::isInstance)
                .filter(JsonFactory::dependenciesAvailable)
                .map(json -> json.deserializer(Envelope.class))
                .findFirst();
    }


}
