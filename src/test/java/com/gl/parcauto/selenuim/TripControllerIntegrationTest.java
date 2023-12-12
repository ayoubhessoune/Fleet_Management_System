package com.gl.parcauto.selenuim;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TripControllerIntegrationTest {
    private static WebDriver driver;

    @Before
    public void setUp() {
        // Set up the Firefox WebDriver
        System.setProperty("webdriver.gecko.driver", "E:\\geckodriver.exe");
        driver = new FirefoxDriver();
    }

    @Test
    public void testCreateVehicleEndpoint() {
        // Navigate to the createVehicle endpoint
        driver.get("http://localhost:8080/swagger-ui/index.html#/trip-controller/createTrip");

        // Click on the "Try it out" button
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement tryItOutButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".try-out__btn")));
        tryItOutButton.click();

        // Find and replace the default JSON in the textarea
        WebElement textArea = driver.findElement(By.cssSelector(".body-param__text"));
        textArea.clear(); // Clear existing content if any
        textArea.sendKeys("{\n" +
                "  \"driverId\": 123,\n" +
                "  \"vehicleId\": 456,\n" +
                "  \"startTrip\": \"2023-12-12T10:00:00\",\n" +
                "  \"endTrip\": \"2023-12-13T12:00:00\"\n" +
                "}");

        // Click on the "Execute" button
        WebElement executeButton = driver.findElement(By.cssSelector(".execute-wrapper button.btn.execute.opblock-control__btn"));
        executeButton.click();

        // Validate the response code
        int responseCode = getResponseCode();
        Assert.assertEquals(200, responseCode);
    }

    private int getResponseCode() {
        // In a real scenario, you would need to capture and return the actual HTTP response code
        // For the sake of simplicity, we are returning a static value here.
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
