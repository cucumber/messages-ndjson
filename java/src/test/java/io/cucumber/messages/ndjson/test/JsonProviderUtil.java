package io.cucumber.messages.ndjson.test;


import io.cucumber.messages.ndjson.Json;

import java.util.stream.Stream;

final class JsonProviderUtil {

    public static Stream<Json> instance(String name) {
        return Json.instances()
                .filter(json -> json.getClass().getSimpleName().equals(name));
    }
}
