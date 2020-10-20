package com.moneymedia.timedsneaky.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.function.Consumer;

import static com.moneymedia.timedsneaky.Constants.SNEAKY;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class SneakyServiceTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void nullJson_throwsIllegalArgumentException() {
        var consumer = applicationContext.getBean(SNEAKY, Consumer.class);
        assertThrows(IllegalArgumentException.class, () -> consumer.accept(null));
    }

    @Test
    void badJson_throwsJsonProcessingException() {
        var consumer = applicationContext.getBean(SNEAKY, Consumer.class);
        assertThrows(JsonProcessingException.class, () -> consumer.accept(""));
    }

}