package io.cucumber.messages.ndjson.test;

import io.cucumber.messages.ndjson.Jackson2;
import io.cucumber.messages.ndjson.Jackson3;
import io.cucumber.messages.ndjson.JsonFactory;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

final class JsonFactoryTest {

    @Test
    void test(){
        var instance = JsonFactory.instance();
        assertThat(instance).isInstanceOfAny(Jackson2.class, Jackson3.class);
    }
}
