package com.moneymedia.timedsneaky.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.function.Consumer;

import static com.moneymedia.timedsneaky.Constants.TIMED_SNEAKY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class TimedSneakyServiceTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void nullJson_throwsIllegalArgumentException() {
        var consumer = applicationContext.getBean(TIMED_SNEAKY, Consumer.class);
        assertThrows(IllegalArgumentException.class, () -> consumer.accept(null));
    }

    @Test
    void badJson_throwsJsonProcessingException() {
        var consumer = applicationContext.getBean(TIMED_SNEAKY, Consumer.class);
        // Life with @Timed and @SneakyThrows on the same method...
        var cause = assertThrows(UndeclaredThrowableException.class, () -> consumer.accept("")).getCause();
        assertThat(cause).isInstanceOf(JsonProcessingException.class);
    }

}