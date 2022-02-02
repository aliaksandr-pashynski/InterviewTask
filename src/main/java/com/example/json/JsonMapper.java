package com.example.json;

public interface JsonMapper {

    <T> T parseJsonToObject(String json, Class<T> targetClass);

    <T> String parseObjectToJson(T obj);
}
