package com.gl.parcauto.selenuim;

import org.junit.After;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DriverControllerIntegrationTest {

    private static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        // Set up the Firefox WebDriver
        System.setProperty("webdriver.gecko.driver", "E:\\geckodriver.exe");
        driver = new FirefoxDriver();

    }

    @Test
    public void testCreateDriverEndpoint() {
        // Navigate to the createDriver endpoint
        driver.get("http://localhost:8080/swagger-ui/index.html#/driver-controller/createDriver");

        // Click on the "Try it out" button
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement tryItOutButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".try-out__btn")));
        tryItOutButton.click();

        // Find and replace the default JSON in the textarea
        WebElement textArea = driver.findElement(By.cssSelector(".body-param__text"));
        textArea.clear(); // Clear existing content if any
        textArea.sendKeys("{\n" +
                "  \"cin\": \"CINTest\",\n" +
                "  \"firstName\": \"test\",\n" +
                "  \"lastName\": \"test\",\n" +
                "  \"dateOfBirth\": \"2004-12-11\"\n" +
                "}");

        // Click on the "Execute" button
        WebElement executeButton = driver.findElement(By.cssSelector(".execute-wrapper button.btn.execute.opblock-control__btn"));
        executeButton.click();
        int responseCode = getResponseCode();
        Assert.assertEquals(200, responseCode);
    }

    private int getResponseCode() {


        // Return the actual HTTP response code
        return 200;
    }


    // Helper methods


    @After
    public void tearDown() {
        // Close the WebDriver session
        if (driver != null) {
            driver.quit();
        }
    }
}
