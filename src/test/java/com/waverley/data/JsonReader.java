package com.waverley.data;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;

import static java.lang.ClassLoader.getSystemResource;

public class JsonReader implements DataReader {
  @Override
  public String getDataType() {
    return "json";
  }

  @Override
  public <T> T readDataFrom(final String dataSource, final Class<T> entityClass) {
    final Gson gson = new Gson();
    final String path = getSystemResource(dataSource).getPath();

    try (final FileReader fileReader = new FileReader(path)) {
      com.google.gson.stream.JsonReader reader = new com.google.gson.stream.JsonReader(fileReader);
      return gson.fromJson(reader, entityClass);
    } catch (IOException e) {
      throw new IllegalArgumentException("Unable to readDataFrom data");
    }
  }
}
