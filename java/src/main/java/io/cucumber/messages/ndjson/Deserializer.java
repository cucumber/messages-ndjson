package io.cucumber.messages.ndjson;

/**
 * Deserializes a JSON string to a message.
 */
public interface Deserializer<T> {

    /**
     * Deserialize a JSON string to message.
     *
     * <ul>
     *     <li>Values must be included unless their value is {@code null}
     *     or an "absent" reference values such as empty optionals.
     *     <li>Enums must be read as strings.
     *     <li>Unknown properties must be ignored.
     * </ul>
     *
     * @param json to deserialize
     * @return a deserialized {@link T} or null
     */
    T readValue(String json);
}
