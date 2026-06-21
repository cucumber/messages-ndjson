package io.cucumber.messages.ndjson.test;


import io.cucumber.messages.ndjson.Deserializer;
import io.cucumber.messages.ndjson.Json;
import io.cucumber.messages.ndjson.Serializer;
import io.cucumber.messages.types.Envelope;

import java.util.Optional;
import java.util.stream.Stream;

final class JsonProviderUtil {

    private JsonProviderUtil(){
        /* no-op */
    }

    public static Stream<Json> instance(String name) {
        return Json.instances()
                .filter(json -> json.getClass().getSimpleName().equals(name));
    }

    static Optional<Serializer<Envelope>> loadDeserializer(String name) {
        return instance(name)
                .map(json -> json.serializer(Envelope.class))
                .findFirst();
    }

    static Optional<Deserializer<Envelope>> loadSerializer(String name) {
        return instance(name)
                .map(json -> json.deserializer(Envelope.class))
                .findFirst();
    }
}
