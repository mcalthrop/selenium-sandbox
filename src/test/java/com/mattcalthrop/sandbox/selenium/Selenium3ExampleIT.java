package com.mattcalthrop.sandbox.selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Selenium3ExampleIT {
    private static WebDriver driver;
    private static WebDriverWait wait;
    private static String startUrl;

    private Boolean result;

    private static void systemInfo() {
        String browser = System.getProperty("browser");
        String operatingSystem = System.getProperty("os.name");
        String systemArchitecture = System.getProperty("os.arch");

        System.out.println("");
        System.out.println("Browser: " + browser);
        System.out.println("OS: " + operatingSystem);
        System.out.println("Architecture: " + systemArchitecture);
        System.out.println("");
        System.out.println("Tests now running...");
        System.out.println("");
    }

    @BeforeClass
    public static void beforeAll() throws Exception {
        systemInfo();
        startUrl = "http://www.google.co.uk/";
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Before
    public void beforeEach() throws Exception {
        driver.get(startUrl);
    }

    @AfterClass
    public static void afterAll() throws Exception {
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
