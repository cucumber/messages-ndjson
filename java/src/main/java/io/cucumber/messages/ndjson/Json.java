package io.cucumber.messages.ndjson;

/**
 * A minimal JSON implementation for use with Cucumber.
 */
public interface Json {

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
