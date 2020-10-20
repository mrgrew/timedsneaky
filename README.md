# Spring Boot Actuator Bug with @Timed and @SneakyThrows

## Overview

This Spring Boot project demonstrates unexpected behavior when annotating a method
as both [@Timed] and [@SneakyThrows].

Services [SneakyService] and [TimedSneakyService] implement the [Consumer<String>]
interface and process the passed string as JSON. Since the [Consumer<String>] interface
doesn't include an exception, the services use the @SneakyThrows annotation to allow the
JsonProcessingException to pass back to the caller. The only difference being
[TimedSneakyService] also annotates accept() with [@Timed].

The [SneakyServiceTest] succeeds. The same tests exist in [TimedSneakyServiceTest] but
the badJson_throwsJsonProcessingException() test fails with an unexpected
`java.lang.reflect.UndeclaredThrowableException`.

## Running the tests

Running the tests demonstrates the problem:
```shell script
mvnw clean test 
```

[@SneakyThrows]: https://projectlombok.org/features/SneakyThrows
[@Timed]: https://micrometer.io/docs/concepts#_the_timed_annotation
[Consumer<String>]: https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/function/Consumer.html
[SneakyService]: src/main/java/com/moneymedia/timedsneaky/service/SneakyService.java
[SneakyServiceTest]: src/test/java/com/moneymedia/timedsneaky/service/SneakyServiceTest.java
[TimedSneakyService]: src/main/java/com/moneymedia/timedsneaky/service/TimedSneakyService.java
[TimedSneakyServiceTest]: src/test/java/com/moneymedia/timedsneaky/service/TimedSneakyServiceTest.java
