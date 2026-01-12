package io.cucumber.messages.ndjson;

import io.cucumber.messages.MessageToNdjsonWriter;
import io.cucumber.messages.types.Envelope;

import java.io.IOException;
import java.io.Writer;

public final class Serializer implements MessageToNdjsonWriter.Serializer {

    /**
     * Converts a given {@link Envelope} to single line of JSON and writes it
     * to the given writer.
     */
    @Override
    public void writeValue(Writer writer, Envelope value) throws IOException {
        Jackson.OBJECT_MAPPER.writeValue(writer, value);
    }
}
