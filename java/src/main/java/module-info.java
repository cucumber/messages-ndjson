import io.cucumber.messages.ndjson.Jackson3;
import io.cucumber.messages.ndjson.Json;

module io.cucumber.messages.ndjson {
    requires org.jspecify;
    requires io.cucumber.messages;

    requires static tools.jackson.databind;

    exports io.cucumber.messages.ndjson;
    provides Json with Jackson3;
}
