package io.cucumber.messages.ndjson.test;

import io.cucumber.messages.ndjson.Deserializer;
import io.cucumber.messages.ndjson.Json;
import io.cucumber.messages.ndjson.Serializer;
import io.cucumber.messages.types.Envelope;
import io.cucumber.messages.types.Source;
import io.cucumber.messages.types.TestRunStarted;
import io.cucumber.messages.types.Timestamp;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import static io.cucumber.messages.types.SourceMediaType.TEXT_X_CUCUMBER_GHERKIN_PLAIN;
import static org.assertj.core.api.Assertions.assertThat;

final class Jackson2Test {

    private final Json jackson2 = JsonProviderUtil.instance("Jackson2").findFirst().orElseThrow();
    private final Deserializer<Envelope> deserializer = jackson2.deserializer(Envelope.class);
    private final Serializer<Envelope> serializer = jackson2.serializer(Envelope.class);

    @Test
    void can_deserialize_enum() {
        var source = Envelope.of(new Source("hello.feature", "Feature: Hello", TEXT_X_CUCUMBER_GHERKIN_PLAIN));
        var json = writeValueAsString(source);
        assertThat(deserializer.readValue(json)).isEqualTo(source);
    }

    @Test
    void serialize_enums_using_value() {
        var source = Envelope.of(new Source("hello.feature", "Feature: Hello", TEXT_X_CUCUMBER_GHERKIN_PLAIN));
        assertThat(writeValueAsString(source))
                .contains("\"text/x.cucumber.gherkin+plain\"");
    }

    @Test
    void can_deserialize_envelope() {
        Envelope source = Envelope.of(new TestRunStarted(new Timestamp(3L, 14), UUID.randomUUID().toString()));
        String json = writeValueAsString(source);
        assertThat(deserializer.readValue(json)).isEqualTo(source);
    }

    private String writeValueAsString(Envelope source) {
        var out =  new ByteArrayOutputStream();
        serializer.writeValue(new OutputStreamWriter(out, StandardCharsets.UTF_8), source);
        return out.toString(StandardCharsets.UTF_8);
    }

}
