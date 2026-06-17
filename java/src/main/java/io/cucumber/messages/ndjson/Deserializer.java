package io.cucumber.messages.ndjson;

import io.cucumber.messages.NdjsonToMessageReader;
import io.cucumber.messages.types.Envelope;

import static java.util.Objects.requireNonNull;

/**
 * Deserializes a JSON string to a message.
 */
public final class Deserializer implements NdjsonToMessageReader.Deserializer {

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
     * @return a deserialized {@link Envelope} or null
     */
    @Override
    public Envelope readValue(String json) {
        requireNonNull(json);
        return Jackson.OBJECT_MAPPER.readValue(json, Envelope.class);
    }
}
