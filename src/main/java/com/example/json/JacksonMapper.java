package com.example.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class JacksonMapper implements JsonMapper {

    private final ObjectMapper objectMapper;
    private static final Logger logger = LoggerFactory.getLogger(JacksonMapper.class);

    public JacksonMapper() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public <T> T parseJsonToObject(String json, Class<T> targetClass) {
        try {
            return objectMapper.readValue(json, targetClass);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    @Override
    public String parseObjectToJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }
}