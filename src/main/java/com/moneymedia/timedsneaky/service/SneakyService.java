package com.moneymedia.timedsneaky.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

import static com.moneymedia.timedsneaky.Constants.SNEAKY;

@Component(SNEAKY)
public class SneakyService implements Consumer<String> {

    private final ObjectReader objectReader;

    public SneakyService(ObjectMapper objectMapper) {
        this.objectReader = objectMapper.reader();
    }

    @Override
    @SneakyThrows(JsonProcessingException.class)
    public void accept(String s) {
        var value = objectReader.readValue(s);
    }

}
