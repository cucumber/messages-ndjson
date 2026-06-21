import io.cucumber.messages.ndjson.Json;
import org.jspecify.annotations.NullMarked;

@NullMarked
module io.cucumber.messages.ndjson.test {
    requires io.cucumber.messages.ndjson;
    requires transitive io.cucumber.messages;

    requires tools.jackson.databind;
    requires org.junit.jupiter.api;
    requires org.junit.jupiter.params;
    requires org.assertj.core;
    requires jsonassert;

    uses io.cucumber.messages.ndjson.JsonProvider;

    opens io.cucumber.messages.ndjson.test;
}
