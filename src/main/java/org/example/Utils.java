package org.example;

import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static final String CHROME_SYSTEM_PROPERTY_NAME = "webdriver.chrome.driver";
    public static final String CHROME_SYSTEM_PROPERTY_PATH = "drivers/chromedriver.exe";
    public static final String FIREFOX_SYSTEM_PROPERTY_NAME = "webdriver.gecko.driver";
    public static final String FIREFOX_SYSTEM_PROPERTY_PATH = "drivers/geckodriver";
    public static final String BASE_URL = "https://mirtesen.ru/";

    static Dotenv dotenv = Dotenv.configure().load();

    public static final String CORRECT_LOGIN = dotenv.get("LOGIN");
    public static final String CORRECT_PASSWORD = dotenv.get("PASSWORD");
    public static final String WRONG_PASSWORD = "***_wrong";

    public static List<WebDriver> getDrivers() {
        List<WebDriver> drivers = new ArrayList<>();
        try {
            List<String> properties = Files.readAllLines(Paths.get("mirtesen.properties"));
            for (String property : properties) {
                if (property.startsWith("WEB_DRIVER")) {
                    switch (property.toLowerCase().split("=")[1]) {
                        case "chrome":
                            drivers.add(getChromeDriver()); return drivers;
                        case "firefox":
                            drivers.add(getFirefoxDriver()); return drivers;
                        case "both":
                            drivers.add(getChromeDriver());
                            drivers.add(getFirefoxDriver());
                            return drivers;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Web driver is not specified");
    }

    private static ChromeDriver getChromeDriver() {
        if (!System.getProperties().containsKey(CHROME_SYSTEM_PROPERTY_NAME)) {
            throw new RuntimeException("Chrome driver not set properly");
        }
        return new ChromeDriver();
    }

    private static FirefoxDriver getFirefoxDriver() {
        if (!System.getProperties().containsKey(FIREFOX_SYSTEM_PROPERTY_NAME)) {
            throw new RuntimeException("Firefox driver not set properly");
        }
        return new FirefoxDriver();
    }


}
