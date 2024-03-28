package org.example;

import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    static Dotenv dotenv = Dotenv.configure().load();

    public static final String CHROME_SYSTEM_PROPERTY_NAME = "webdriver.chrome.driver";
    public static final String CHROME_SYSTEM_PROPERTY_PATH = dotenv.get("CHROME_SYSTEM_PROPERTY_PATH");
    public static final String FIREFOX_SYSTEM_PROPERTY_NAME = "webdriver.gecko.driver";
    public static final String FIREFOX_SYSTEM_PROPERTY_PATH = dotenv.get("FIREFOX_SYSTEM_PROPERTY_PATH");
    public static final String BASE_URL = "https://mirtesen.ru/";
    public static final String FIRST_USER_URL = "https://mirtesen.ru/people/914760988";
    public static final String SECOND_USER_URL = "https://mirtesen.ru/people/68403438";

    public static final String CORRECT_FIRST_USER_LOGIN = dotenv.get("DANS_LOGIN");
    public static final String CORRECT_FIRST_USER_PASSWORD = dotenv.get("DANS_PASSWORD");
    public static final String CORRECT_SECOND_USER_LOGIN = dotenv.get("MAXES_LOGIN");
    public static final String CORRECT_SECOND_USER_PASSWORD = dotenv.get("MAXES_PASSWORD");
    public static final String WRONG_FIRST_USER_PASSWORD = "wrong_password";

    public static WebDriver getDriver() {
        WebDriver driver;
        try {
            List<String> properties = Files.readAllLines(Paths.get("mirtesen.properties"));
            for (String property : properties) {
                if (property.startsWith("WEB_DRIVER")) {
                    switch (property.toLowerCase().split("=")[1]) {
                        case "chrome":
                            driver = getChromeDriver(); return driver;
                        case "firefox":
                            driver = getFirefoxDriver(); return driver;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Веб-драйвер не указан");
    }

    private static ChromeDriver getChromeDriver() {
        if (!System.getProperties().containsKey(CHROME_SYSTEM_PROPERTY_NAME)) {
            throw new RuntimeException("Драйвер Chrome установлен неправильно");
        }
        return new ChromeDriver();
    }

    private static FirefoxDriver getFirefoxDriver() {
        if (!System.getProperties().containsKey(FIREFOX_SYSTEM_PROPERTY_NAME)) {
            throw new RuntimeException("Драйвер Firefox установлен неправильно");
        }
        return new FirefoxDriver();
    }

    public static void prepareDrivers() {
        System.setProperty(CHROME_SYSTEM_PROPERTY_NAME, CHROME_SYSTEM_PROPERTY_PATH);
        System.setProperty(FIREFOX_SYSTEM_PROPERTY_NAME, FIREFOX_SYSTEM_PROPERTY_PATH);
    }

    public static WebElement getElementBySelector(WebDriver driver, By selector) {
        WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return driverWait.until(ExpectedConditions.visibilityOfElementLocated(selector));
    }

    public static void waitUntilPageLoads(WebDriver driver, long timeout) {
        WebDriverWait waitDriver = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        waitDriver.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }
}
