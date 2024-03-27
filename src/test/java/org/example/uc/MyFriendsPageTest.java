package org.example.uc;

import org.example.Utils;
import org.example.pages.MainPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MyFriendsPageTest {

    @BeforeAll
    public static void prepareDrivers() {
        Utils.prepareDrivers();
    }

    @Test
    public void deleteFriendTest() {
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
}
