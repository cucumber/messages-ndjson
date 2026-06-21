package io.cucumber.messages.ndjson;

import io.cucumber.messages.Property;
import tools.jackson.core.StreamWriteFeature;
import tools.jackson.core.Version;
import tools.jackson.databind.AnnotationIntrospector;
import tools.jackson.databind.PropertyName;
import tools.jackson.databind.cfg.ConstructorDetector;
import tools.jackson.databind.cfg.MapperConfig;
import tools.jackson.databind.introspect.Annotated;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.module.SimpleModule;

import java.io.Serial;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_ABSENT;

final class Jackson3 implements Json {

    JsonMapper delegate = JsonMapper.builder()
            .addModule(new CucumberParameterNamesModule())
            .changeDefaultPropertyInclusion(value -> value
                    .withContentInclusion(NON_ABSENT)
                    .withValueInclusion(NON_ABSENT)
            )
            .constructorDetector(ConstructorDetector.USE_PROPERTIES_BASED)
            .disable(StreamWriteFeature.AUTO_CLOSE_TARGET)
            .build();


    @Override
    public <T> Deserializer<T> deserializer(Class<T> type) {
        return json -> delegate.readValue(json, type);
    }

    @Override
    public <T> Serializer<T> serializer(Class<T> type) {
        return (writer, value) -> delegate.writeValue(writer, value);
    }

    static final class CucumberParameterNamesModule extends SimpleModule {
        @Serial
        private static final long serialVersionUID = 1L;

        CucumberParameterNamesModule() {
            super("CucumberParameterNamesModule");
        }

        @Override
        public void setupModule(SetupContext context) {
            super.setupModule(context);
            context.insertAnnotationIntrospector(new CucumberParameterNamesModule.PropertyNameAnnotationIntrospector());
        }

        @Override
        public int hashCode() {
            return getClass().hashCode();
        }

        @Override
        public boolean equals(Object o) {
            return this == o;
        }

        static final class PropertyNameAnnotationIntrospector extends AnnotationIntrospector {
            @Serial
            private static final long serialVersionUID = 1L;

            @Override
            public PropertyName findNameForDeserialization(MapperConfig<?> config, Annotated a) {
                Property annotation = a.getAnnotation(Property.class);
                if (annotation == null) {
                    return PropertyName.USE_DEFAULT;
                }
                return PropertyName.construct(annotation.value());
            }

            @Override
            public Version version() {
                return Version.unknownVersion();
            }
        }
    }
}
