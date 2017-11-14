package com.waverley.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class Browser {

    public WebDriver driver;

    @Parameters("browser")

    @BeforeClass

    public void beforeTest(String browser) {

        if(browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "src/main/test/java/resources/chromedriver");
            driver = new ChromeDriver();
        }else if (browser.equalsIgnoreCase("firefox")) {
            System.out.println("CHROME is enough");
        }
        driver.get("http://facebook.com");

    }

    @Test public void login() throws InterruptedException {
        driver.findElement(By.id("email")).sendKeys("testuser_1");
        driver.findElement(By.id("pass")).sendKeys("Test@123");
        driver.findElement(By.id("u_0_3")).click();
        assertEquals("Facebook" , driver.getTitle());
    }

    @AfterClass public void afterTest() {
        driver.quit();
    }
}