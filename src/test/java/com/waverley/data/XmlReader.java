package com.waverley.data;

import io.vavr.control.Try;

import javax.xml.bind.JAXBContext;

import static java.lang.ClassLoader.getSystemResource;
import static javax.xml.bind.JAXBContext.newInstance;

public class XmlReader implements DataReader {
  @Override
  public String getDataType() {
    return "xml";
  }

  @Override
  @SuppressWarnings("unchecked")
  public <T> T readDataFrom(final String dataSource, final Class<T> entityClass) {
    return Try.of(() -> newInstance(entityClass))
        .mapTry(JAXBContext::createUnmarshaller)
        .mapTry(unmarshaller -> (T) unmarshaller.unmarshal(getSystemResource(dataSource)))
        .getOrElseThrow(
            e -> new IllegalArgumentException("Unable to readDataFrom " + dataSource, e));
  }
}
