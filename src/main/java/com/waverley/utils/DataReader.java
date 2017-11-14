package com.waverley.utils;

/**
 * Interface that will perform read from any data file source.
 */

public interface DataReader {
         <T> T read(String dataSource, Class<T> entityClass);
 }
