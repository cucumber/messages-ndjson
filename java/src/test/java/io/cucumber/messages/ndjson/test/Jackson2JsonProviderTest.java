package io.cucumber.messages.ndjson.test;

import io.cucumber.messages.ndjson.test.ClassLoaderUtil.FilteredClassLoader;
import org.junit.jupiter.api.Test;

import static io.cucumber.messages.ndjson.test.ClassLoaderUtil.executeWith;
import static io.cucumber.messages.ndjson.test.JsonUtil.loadDeserializer;
import static io.cucumber.messages.ndjson.test.JsonUtil.loadSerializer;
import static org.assertj.core.api.Assertions.assertThat;

final class Jackson2JsonProviderTest {

    @Test
    void testSerializer() {
        assertThat(loadDeserializer("Jackson2")).isNotEmpty();
    }

    @Test
    void testSerializerWithoutDependency() {
        var filteredClassLoader = new FilteredClassLoader("com.fasterxml.jackson.");
        executeWith(filteredClassLoader, () -> assertThat(loadSerializer("Jackson2")).isEmpty());
    }

    @Test
    void testDeserializer() {
        assertThat(loadSerializer("Jackson2")).isNotEmpty();
    }

    @Test
    void testDeserializerWithoutDependency() {
        var filteredClassLoader = new FilteredClassLoader("com.fasterxml.jackson.");
        executeWith(filteredClassLoader, () -> assertThat(loadSerializer("Jackson2")).isEmpty());
    }
}
