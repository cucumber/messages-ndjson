package io.cucumber.messages.ndjson;

import io.cucumber.messages.NdjsonToMessageIterable;
import io.cucumber.messages.types.Envelope;

import java.io.IOException;

import static java.util.Objects.requireNonNull;

public final class Deserializer implements NdjsonToMessageIterable.Deserializer {

    /**
     * Reads the given {@code json} and converts it to a link {@link Envelope}
     */
    @Override
    public Envelope readValue(String json) throws IOException {
        requireNonNull(json);
        return Jackson.OBJECT_MAPPER.readValue(json, Envelope.class);
    }
}
