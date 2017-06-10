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
    private WebDriverWait wait;
    private Boolean result;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("http://www.google.co.uk/");
        wait = new WebDriverWait(driver, 10);
    }

    @After
    public void tearDown() {
        driver.close();
        driver.quit();
    }

    @Test
    public void checkPageTitleOnInit() throws Exception {
        assertEquals("Google", driver.getTitle());
    }

    @Test
    public void checkPageTitleAfterSearch() throws Exception {
        WebElement searchField = driver.findElement(By.name("q"));
        searchField.sendKeys("Sausages!");
        searchField.submit();
        // Google's search is rendered dynamically with JavaScript, so wait for the page to load.
        result = wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return driver.getTitle().toLowerCase().startsWith("sausages!");
            }
        });
        assertTrue(result);
    }

}
