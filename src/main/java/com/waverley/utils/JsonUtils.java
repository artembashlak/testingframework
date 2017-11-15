package com.waverley.utils;

import io.vavr.control.Try;

import javax.xml.bind.JAXBContext;

import static com.waverley.utils.ServiceLoaderUtils.load;
import static java.lang.ClassLoader.getSystemResource;
import static javax.xml.bind.JAXBContext.newInstance;

/** Util that get data from json file. */
public class JsonUtils {
@SuppressWarnings("unchecked")
  public static <T> T jsonToEntity(final String dataSource, final Class<T> entityClass) {
    return (T) load(entityClass, ClassLoader.getSystemClassLoader());
  }

  @SuppressWarnings("unchecked")
  public static <T> T xmlToEntity(final String dataSource, final Class<T> entityClass) {
    return Try.of(() -> newInstance(entityClass))
        .mapTry(JAXBContext::createUnmarshaller)
        .mapTry(unmarshaller -> (T) unmarshaller.unmarshal(getSystemResource(dataSource)))
        .getOrElseThrow(
            e -> new IllegalArgumentException("Unable to readDataFrom " + dataSource, e));
  }
}
