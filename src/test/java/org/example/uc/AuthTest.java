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
        WebDriver webDriver = Utils.getDriver();

        MainPage mainPage = new MainPage(webDriver);
        webDriver.get(Utils.BASE_URL);
        mainPage.doFirstUserLogin();
        WebElement burgerMenu = Utils.getElementBySelector(webDriver, By.xpath("//*[@id=\"header-profile-tooltip\"]/button"));
        assertNotNull(burgerMenu);

        webDriver.quit();
    }

    @Test
    void logoutTest() {
        WebDriver webDriver = Utils.getDriver();

        MainPage mainPage = new MainPage(webDriver);
        webDriver.get(Utils.BASE_URL);
        mainPage.doFirstUserLogin();
        mainPage.doLogout();
        WebElement authButton = Utils.getElementBySelector(webDriver, By.xpath("/html/body/div[1]/div/div[1]/div[1]/header/div/div/div/div/div[4]/ul/li[2]/button"));
        assertNotNull(authButton);

        webDriver.quit();
    }

    @Test
    void wrongLoginTest() {
        WebDriver webDriver = Utils.getDriver();

        MainPage mainPage = new MainPage(webDriver);
        webDriver.get(Utils.BASE_URL);
        mainPage.doWrongLogin();
        WebElement loginError = Utils.getElementBySelector(webDriver, By.xpath("/html/body/div[3]/div/div/div/div/div/form/div[2]/div/div[1]"));
        assertNotNull(loginError);

        webDriver.quit();
    }
}
