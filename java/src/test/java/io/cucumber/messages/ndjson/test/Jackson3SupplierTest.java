package io.cucumber.messages.ndjson.test;

import io.cucumber.messages.ndjson.Jackson3;
import io.cucumber.messages.ndjson.Json;
import io.cucumber.messages.types.Envelope;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.ServiceLoader;

import static org.assertj.core.api.Assertions.assertThat;

class Jackson3SupplierTest {
    
    @Test
    void testSerializer() {
        var load = ServiceLoader.load(Json.class);
        var serializer = load.stream()
                .map(ServiceLoader.Provider::get)
                .filter(Jackson3.class::isInstance)
                .map(json -> json.serializer(Envelope.class))
                .flatMap(Optional::stream)
                .findFirst();

        assertThat(serializer)
                .map(Object::getClass)
                .map(Class::getName)
                .contains("io.cucumber.messages.ndjson.Jackson3JsonMapper");
    }

    @Test
    void testDeserializer() {
        var load = ServiceLoader.load(Json.class);
        var deserializer = load.stream()
                .map(ServiceLoader.Provider::get)
                .filter(Jackson3.class::isInstance)
                .map(json -> json.deserializer(Envelope.class))
                .flatMap(Optional::stream)
                .findFirst();

        assertThat(deserializer)
                .map(Object::getClass)
                .map(Class::getName)
                .contains("io.cucumber.messages.ndjson.Jackson3JsonMapper");
    }

    @Test
    void test2() {
        Thread.currentThread().setContextClassLoader(new ClassLoader() {
            @Override
            protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
                if (name.startsWith("tools.jackson.")) {
                    throw new ClassNotFoundException();
                }
                return super.loadClass(name, resolve);
            }
        });

        var load = ServiceLoader.load(Json.class);
        var deserializer = load.stream()
                .map(ServiceLoader.Provider::get)
                .filter(Jackson3.class::isInstance)
                .map(json -> json.deserializer(Envelope.class))
                .flatMap(Optional::stream)
                .findFirst();

        assertThat(deserializer).isEmpty();
    }

}
