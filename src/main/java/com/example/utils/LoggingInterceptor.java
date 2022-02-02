package com.example.utils;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class LoggingInterceptor implements Interceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        logger.info(String.format("Sending %s request to %s", request.method(), request.url()));
        Response response = chain.proceed(request);
        logger.info(String.format("Status code: %s", response.code()));
        ResponseBody responseBodyCopy = response.peekBody(Long.MAX_VALUE);
        logger.info(String.format("Body: %s", responseBodyCopy.string()));
        return response;
    }

}