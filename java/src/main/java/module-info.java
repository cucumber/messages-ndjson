import io.cucumber.messages.ndjson.Jackson2JsonProvider;
import io.cucumber.messages.ndjson.Jackson3JsonProvider;
import io.cucumber.messages.ndjson.JsonProvider;
import org.jspecify.annotations.NullMarked;

@NullMarked
module io.cucumber.messages.ndjson {
    requires org.jspecify;
    requires io.cucumber.messages;

    requires static tools.jackson.databind;
    requires static com.fasterxml.jackson.databind;
    requires static com.fasterxml.jackson.datatype.jdk8;
    requires static com.fasterxml.jackson.core;

    exports io.cucumber.messages.ndjson;

    provides JsonProvider with Jackson2JsonProvider, Jackson3JsonProvider;
    uses JsonProvider;
}
