import io.cucumber.messages.ndjson.JsonFactory;
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

    uses JsonFactory;

    opens io.cucumber.messages.ndjson.test;
}
