package com.waverley.testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseClass {
    @BeforeClass
    public void beforeMethod() {
        System.out.println("Run before each class.");
    }

    @AfterClass
    public void afterMethod() {
        System.out.println("Run after each class.");
    }
}
