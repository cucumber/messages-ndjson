import io.cucumber.messages.ndjson.Jackson2;
import io.cucumber.messages.ndjson.Jackson3;
import io.cucumber.messages.ndjson.JsonFactory;
import org.jspecify.annotations.NullMarked;

@NullMarked
module io.cucumber.messages.ndjson {
    uses JsonFactory;
    requires org.jspecify;
    requires io.cucumber.messages;

    requires static tools.jackson.databind;
    requires static com.fasterxml.jackson.databind;
    requires static com.fasterxml.jackson.datatype.jdk8;
    requires static com.fasterxml.jackson.core;

    exports io.cucumber.messages.ndjson;
    provides JsonFactory with Jackson2, Jackson3;
}
