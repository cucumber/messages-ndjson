package io.cucumber.messages.ndjson;

import io.cucumber.messages.MessageToNdjsonWriter;
import io.cucumber.messages.types.Envelope;

import java.io.IOException;
import java.io.Writer;

import static java.util.Objects.requireNonNull;

/**
 * Serializes a message to a single line of JSON.
 */
public final class Serializer implements MessageToNdjsonWriter.Serializer {

    /**
     * Serialize a message to single line of JSON and write it to the
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
     * @throws IOException if anything goes wrong
     */
    @Override
    public void writeValue(Writer writer, Envelope value) throws IOException {
        requireNonNull(writer);
        requireNonNull(value);
        Jackson.OBJECT_MAPPER.writeValue(writer, value);
    }
}
