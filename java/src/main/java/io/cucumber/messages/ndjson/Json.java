package io.cucumber.messages.ndjson;

import java.util.Optional;
import java.util.ServiceLoader;
import java.util.stream.Stream;

/**
 * A minimal JSON implementation for use with Cucumber.
 */
public interface Json {

    /**
     * Returns a stream of {@code Json} instances discovered using SPI.
     *
     * @return a stream of {@code Json} instances.
     */
    static Stream<Json> instances(){
        return ServiceLoader.load(JsonProvider.class).stream()
                .map(ServiceLoader.Provider::get)
                .map(JsonProvider::instance)
                .filter(Optional::isPresent)
                .map(Optional::get);
    }

    /**
     * Creates a deserializer for {@code type}.
     *
     * @param type the type to deserialize to.
     * @return a deserializer for {@code type}.
     * @param <T> the type.
     */
    <T> Deserializer<T> deserializer(Class<T> type);


    /**
     * Creates a serializer for {@code type}.
     *
     * @param type the to deserialize from.
     * @return a serializer for {@code type}.
     * @param <T> the type.
     */
    <T> Serializer<T> serializer(Class<T> type);

}
