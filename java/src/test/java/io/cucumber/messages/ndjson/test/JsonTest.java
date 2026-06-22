package io.cucumber.messages.ndjson.test;

import io.cucumber.messages.ndjson.Json;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

final class JsonTest {

    @Test
    void test(){
        assertThat(Json.instance()).isNotEmpty();
    }
}
