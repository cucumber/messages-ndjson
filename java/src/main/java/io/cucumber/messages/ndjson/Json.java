package io.cucumber.messages.ndjson;

import java.util.Optional;
import java.util.ServiceLoader;

/**
 * A minimal JSON implementation for use with Cucumber.
 */
public interface Json {

    /**
     * Creates a deserializer for {@code type}.
     *
     * @param type the type to deserialize to.
     * @param <T>  the type.
     * @return a deserializer for {@code type}.
     */
    <T> Deserializer<T> deserializer(Class<T> type);


    /**
     * Creates a serializer for {@code type}.
     *
     * @param type the to deserialize from.
     * @param <T>  the type.
     * @return a serializer for {@code type}.
     */
    <T> Serializer<T> serializer(Class<T> type);

    /**
     * Returns a {@link Json} instance from some {@link JsonProvider} that
     * can provide one.
     *
     * @return an optional contains {@link  Json} instance, or empty if no
     * provider was able to provide one.
     */
    static Optional<Json> instance() {
        return ServiceLoader.load(JsonProvider.class).stream()
                .map(ServiceLoader.Provider::get)
                .map(JsonProvider::instance)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst();
    }
}
