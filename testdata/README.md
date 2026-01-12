# Acceptance test data

The examples from the [cucumber compatibility kit](https://github.com/cucumber/compatibility-kit)
are used for acceptance testing. These examples consist of `.ndjson` files created by
the [`fake-cucumber` reference implementation](https://github.com/cucumber/fake-cucumber).

The `.njdon` files are copied in by running `npm install`.  We ensure the
`.ndjson` files stay up to date by running `npm install` in CI  and verifying
nothing changed.

Should there be changes, these tests can be used to update the expected data for
each implementation:
 * Java: `MessagesToJsonWriterAcceptanceTest.updateExpectedFiles`

## Usage

### Reading

```java
Path path = ...
Deserializer deserializer = new Deserializer(); 
try (NdjsonToMessageReader reader = new NdjsonToMessageReader(newInputStream(path), deserializer)) {
    return reader.lines()
    // Do something with messages
    .collect(Collectors.toList());
}
```

### Writing

```java
Path path = ...
Serializer serializer = new Serializer();
try(MessageToNdjsonWriter writer = new MessageToNdjsonWriter(newOutputStream(path), serializer)){
    for(Envelope envelope : messages){
        writer.write(envelope);
    }
}
```