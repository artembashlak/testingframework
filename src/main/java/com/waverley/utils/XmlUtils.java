package com.waverley.utils;

import io.vavr.control.Try;
import javax.xml.bind.JAXBContext;
import java.io.File;
import static java.lang.ClassLoader.getSystemResource;
import static javax.xml.bind.JAXBContext.newInstance;

/**
 *  A simple class for reading Json files.
 */

public class XmlUtils implements DataReader {

    @Override
    public <T> T read(String dataSource, Class<T> entityClass) {
        return Try.of(() -> newInstance(entityClass))
                .mapTry(JAXBContext::createUnmarshaller)
                .mapTry(unmarshaller -> (T) unmarshaller.unmarshal(new File(getSystemResource(dataSource).getPath())))
                .getOrElseThrow(e -> new IllegalArgumentException("Unable to read " + dataSource, e));
    }
}
