package io.cucumber.messages.ndjson.test;

import io.cucumber.messages.ndjson.test.ClassLoaderUtil.FilteredClassLoader;
import org.junit.jupiter.api.Test;

import static io.cucumber.messages.ndjson.test.ClassLoaderUtil.executeWith;
import static io.cucumber.messages.ndjson.test.JsonUtil.loadDeserializer;
import static io.cucumber.messages.ndjson.test.JsonUtil.loadSerializer;
import static org.assertj.core.api.Assertions.assertThat;

final class Jackson3JsonProviderTest {

    @Test
    void testSerializer() {
        assertThat(loadDeserializer("Jackson3")).isNotEmpty();
    }

    @Test
    void testSerializerWithoutDependency() {
        var filteredClassLoader = new FilteredClassLoader("tools.jackson.");
        executeWith(filteredClassLoader, () -> assertThat(loadSerializer("Jackson3")).isEmpty());
    }

    @Test
    void testDeserializer() {
        assertThat(loadSerializer("Jackson3")).isNotEmpty();
    }

    @Test
    void testDeserializerWithoutDependency() {
        var filteredClassLoader = new FilteredClassLoader("tools.jackson.");
        executeWith(filteredClassLoader, () -> assertThat(loadSerializer("Jackson3")).isEmpty());
    }
}
