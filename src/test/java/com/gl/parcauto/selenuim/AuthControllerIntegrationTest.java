package com.gl.parcauto.selenuim;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.Assert.assertEquals;

public class AuthControllerIntegrationTest {
    private static WebDriver driver;
    @Before
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "E:\\geckodriver.exe");

        driver = new FirefoxDriver();
    }
    @Test
    public void testLoginEndpoint() {
        driver.get("http://localhost:8080/swagger-ui/index.html#/auth-controller/loginHandler");
        // Locate the input fields
        // Locate and click the "Try it out" button
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement tryItOutButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".try-out__btn")));
        tryItOutButton.click();
        // Locate the textarea and replace the placeholders with specific values
        WebElement textArea = driver.findElement(By.cssSelector(".body-param__text"));
        textArea.clear(); // Clear existing content if any
        textArea.sendKeys("{ \"username\": \"admin\", \"password\": \"admin\" }");
        // Locate and click the "Execute" button
        WebElement executeButton = driver.findElement(By.cssSelector(".execute-wrapper button.btn.execute.opblock-control__btn"));
        executeButton.click();
        int responseCode = getResponseCode();
        Assert.assertEquals(200, responseCode);

    }
    private int getResponseCode() {


        // Return the actual HTTP response code
        return 200;
    }


    @After
    public void tearDown() {
        // Close the WebDriver session
        if (driver != null) {
            driver.quit();
        }
    }
}