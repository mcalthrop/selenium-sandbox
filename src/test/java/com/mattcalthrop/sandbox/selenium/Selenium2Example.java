package com.mattcalthrop.sandbox.selenium;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Selenium2Example {
    private String baseUrl;
    private WebDriver driver;

    @Before
    public void openBrowser() {
        baseUrl = System.getProperty("webdriver.base.url");
        driver = new ChromeDriver();
        driver.get(baseUrl);
    }

    @After
    public void saveScreenshotAndCloseBrowser() {
        driver.quit();
    }

    @Test
    public void pageTitleAfterSearchShouldBeginWithDrupal() {
        assertEquals("The page title should equal Google at the start of the test.", "Google", driver.getTitle());
        WebElement searchField = driver.findElement(By.name("q"));
        searchField.sendKeys("Drupal!");
        searchField.submit();
        assertTrue("The page title should start with the search string after the search.",
                (new WebDriverWait(driver, 10)).until(new ExpectedCondition() {
                    public Boolean apply(WebDriver d) {
                        return d.getTitle().toLowerCase().startsWith("drupal!");
                    }
                })
        );
    }

}
