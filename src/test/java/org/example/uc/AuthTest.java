package org.example.uc;

import org.example.Utils;
import org.example.pages.MainPage;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AuthTest {

    @BeforeAll
    public static void prepareDrivers() {
        Utils.prepareDrivers();
    }

    @Test
    void loginTest() {
        List<WebDriver> drivers = Utils.getDrivers();
        drivers.parallelStream().forEach(webDriver -> {
            MainPage mainPage = new MainPage(webDriver);
            webDriver.get(Utils.BASE_URL);
            mainPage.doFirstUserLogin();
            WebElement burgerMenu = Utils.getElementBySelector(webDriver, By.xpath("//*[@id=\"header-profile-tooltip\"]/button"));
            assertNotNull(burgerMenu);
        });
        drivers.forEach(WebDriver::quit);
    }

    @Test
    void logoutTest() {
        List<WebDriver> drivers = Utils.getDrivers();
        drivers.parallelStream().forEach(webDriver -> {
            MainPage mainPage = new MainPage(webDriver);
            webDriver.get(Utils.BASE_URL);
            mainPage.doFirstUserLogin();
            mainPage.doLogout();
            WebElement burgerMenu = Utils.getElementBySelector(webDriver, By.xpath("//*[@id=\"header-profile-tooltip\"]/button"));
            assertNotNull(burgerMenu);
        });
        drivers.forEach(WebDriver::quit);
    }

    @Test
    void wrongLoginTest() {
        List<WebDriver> drivers = Utils.getDrivers();
        drivers.parallelStream().forEach(webDriver -> {
            MainPage mainPage = new MainPage(webDriver);
            webDriver.get(Utils.BASE_URL);
            mainPage.doWrongLogin();
            WebElement loginError = Utils.getElementBySelector(webDriver, By.xpath("/html/body/div[3]/div/div/div/div/div/form/div[2]/div/div[1]"));
            assertNotNull(loginError);
        });
        drivers.forEach(WebDriver::quit);
    }
}
