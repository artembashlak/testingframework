package com.waverley.utils;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.IOException;

/**
 * Util that get data from json file.
 */

public final class JsonUtils {

    private JsonUtils() {
        throw new UnsupportedOperationException("Illegal access to private constructor.");
    }

    public static <T> T jsonToEntity(final String dataSource, final Class<T> entityClass) {
        final Gson gson = new Gson();
        final String path = ClassLoader.getSystemResource(dataSource).getPath();

        try (final FileReader fileReader = new FileReader(path)) {
            JsonReader reader = new JsonReader(fileReader);
            return gson.fromJson(reader, entityClass);
        } catch (IOException e) {
            throw new IllegalArgumentException("Unable to read data");
        }
    }
}
