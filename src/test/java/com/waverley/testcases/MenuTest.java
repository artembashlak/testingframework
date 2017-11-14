package com.waverley.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MenuTest extends BaseClass {
    @BeforeMethod
    public void beforeEachMethod() {
        System.out.println("Before method of Menu class.");
    }

    @AfterMethod
    public void afterEachMethod() {
        System.out.println("After method of Menu class.");
    }

    @Test
    public void userCanOpenContactsPage() {
        System.out.println("Contacts page was opened.");
    }

    @Test
    public void userCanOpenAboutPage() {
        System.out.println("About page was opened.");
    }
}