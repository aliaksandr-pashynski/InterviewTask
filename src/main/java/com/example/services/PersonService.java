package com.example.services;

import com.example.entities.Person;
import com.example.json.JacksonMapper;
import com.example.json.JsonMapper;
import com.example.utils.LoggingInterceptor;
import com.example.utils.ResponseDetails;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class PersonService implements EntityService<Person> {

    private final OkHttpClient client;
    private final Supplier<HttpUrl.Builder> urlBuilder;
    private final JsonMapper jsonMapper = new JacksonMapper();

    public PersonService(String baseUri) {
        this.client = new OkHttpClient()
                .newBuilder()
                .addInterceptor(new LoggingInterceptor())
                .build();
        this.urlBuilder = () -> new HttpUrl.Builder().scheme("https").host(baseUri);
    }

    @Override
    public ResponseDetails<Person> getByName(String name) throws Exception {
        HttpUrl url = urlBuilder.get().addQueryParameter("name", name).build();
        Response response = this.executeRequest(url);
        byte[] bytes = response.peekBody(Long.MAX_VALUE).bytes();
        return new ResponseDetails<>(response.code(),
                jsonMapper.parseJsonToObject(new String(bytes), Person.class),
                bytes);
    }

    @Override
    public ResponseDetails<List<Person>> getAll(List<String> names) throws Exception {
        HttpUrl.Builder builder = urlBuilder.get();
        for (String name : names) {
            builder.addQueryParameter("name", name);
        }
        HttpUrl url = builder.build();
        Response response = this.executeRequest(url);
        byte[] bytes = response.peekBody(Long.MAX_VALUE).bytes();
        return new ResponseDetails<>(response.code(),
                Arrays.asList(jsonMapper.parseJsonToObject(new String(bytes), Person[].class)),
                bytes);
    }

    private Response executeRequest(HttpUrl url) throws IOException {
        return client.newCall(new Request.Builder().url(url).build()).execute();
    }
}
