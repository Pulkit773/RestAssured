package com.spotify.oauth2.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class ObjectMapperHelper {
    public static <T> T stringToObject(String string, Class<T> classType) throws IOException {
        return new ObjectMapper().readValue(string, classType);
    }

    public static <T> String objectToString(T t) throws IOException {
        return new ObjectMapper().writeValueAsString(t);
    }
}
