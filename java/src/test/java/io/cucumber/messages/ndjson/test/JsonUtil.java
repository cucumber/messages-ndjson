package io.cucumber.messages.ndjson.test;

import io.cucumber.messages.ndjson.Json;
import io.cucumber.messages.ndjson.JsonProvider;

import java.util.Optional;
import java.util.ServiceLoader;
import java.util.stream.Stream;

final class JsonUtil {

    static Stream<Json> instances(){
        return ServiceLoader.load(JsonProvider.class).stream()
                .map(ServiceLoader.Provider::get)
                .map(JsonProvider::instance)
                .filter(Optional::isPresent)
                .map(Optional::get);
    }
}
