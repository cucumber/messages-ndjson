package io.cucumber.messages.ndjson;

import java.io.Writer;

/**
 * Serializes a message to a single line of JSON.
 */
public interface Serializer<T> {

    /**
     * Serialize an object to single line of JSON and write it to the
     * given {@code writer}.
     *
     * <ul>
     *     <li>Values must be included unless their value is {@code null}
     *     or an "absent" reference values such as empty optionals.
     *     <li>Enums must be written as strings.
     *     <li>Implementations may not close the {@code writer} after
     *     writing a {@code value}.
     * </ul>
     *
     * @param writer to write to
     * @param value  to serialize
     */
    void writeValue(Writer writer, T value);
}
