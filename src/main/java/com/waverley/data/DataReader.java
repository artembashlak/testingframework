package com.waverley.data;

/** Interface that will perform readDataFrom from any data file source. */
public interface DataReader {

  String getDataType();

  <T> T readDataFrom(String dataSource, Class<T> entityClass);
}
