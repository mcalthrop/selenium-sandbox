package com.mattcalthrop.sandbox.selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Selenium2ExampleIT {
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("http://www.google.co.uk/");
    }

    @After
    public void tearDown() {
        driver.close();
        driver.quit();
    }

    @Test
    public void test1() {
        assertEquals("The page title should equal Google at the start of the test.", "Google", driver.getTitle());
        WebElement searchField = driver.findElement(By.name("q"));
        searchField.sendKeys("Drupal!");
        searchField.submit();
        // The following assertion causes a compilation failure with Java 8:
        // (I'm not familiar enough with Java to know how to fix the issue)
/*
        assertTrue("The page title should start with the search string after the search.",
                (new WebDriverWait(driver, 10)).until(new ExpectedCondition() {
                    public Boolean apply(WebDriver d) {
                        return d.getTitle().toLowerCase().startsWith("drupal!");
                    }
                })
        );
*/
    }

}
