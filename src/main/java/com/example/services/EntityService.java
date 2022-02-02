package com.example.services;

import com.example.utils.ResponseDetails;

import java.util.List;

public interface EntityService<T> {

    ResponseDetails<T> getByName(String name) throws Exception;

    ResponseDetails<List<T>> getAll(List<String> names) throws Exception;
}
