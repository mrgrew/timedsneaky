package com.moneymedia.timedsneaky.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import io.micrometer.core.annotation.Timed;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

import static com.moneymedia.timedsneaky.Constants.TIMED_SNEAKY;
import static com.moneymedia.timedsneaky.Constants.TIMED_SNEAKY_TIMER;

@Component(TIMED_SNEAKY)
public class TimedSneakyService implements Consumer<String> {

    private final ObjectReader objectReader;

    public TimedSneakyService(ObjectMapper objectMapper) {
        this.objectReader = objectMapper.reader();
    }

    @Override
    @Timed(TIMED_SNEAKY_TIMER)
    @SneakyThrows(JsonProcessingException.class)
    public void accept(String s) {
        var value = objectReader.readValue(s);
    }

}
