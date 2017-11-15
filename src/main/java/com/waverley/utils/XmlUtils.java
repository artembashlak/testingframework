package com.waverley.utils;

import com.waverley.data.DataReader;
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
    public String getDataType() {
        return "xml";
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T readDataFrom(String dataSource, Class<T> entityClass) {
        return Try.of(() -> newInstance(entityClass))
                .mapTry(JAXBContext::createUnmarshaller)
                .mapTry(unmarshaller -> (T) unmarshaller.unmarshal(new File(getSystemResource(dataSource).getPath())))
                .getOrElseThrow(e -> new IllegalArgumentException("Unable to readDataFrom " + dataSource, e));
    }
}
