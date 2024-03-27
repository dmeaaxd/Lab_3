//package org.example.uc;
//
//import org.example.Utils;
//import org.example.pages.MainPage;
//import org.junit.jupiter.api.*;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class UserPageTest {
//    @BeforeAll
//    public static void init() {
//        Utils.prepareDrivers();
//
//        List<WebDriver> drivers = Utils.getDrivers();
//        drivers.parallelStream().forEach(webDriver -> {
//            MainPage mainPage = new MainPage(webDriver);
//            webDriver.get(Utils.BASE_URL);
//            mainPage.doFirstUserLogin();
//            WebElement burgerMenu = Utils.getElementBySelector(webDriver, By.xpath("//*[@id=\"header-profile-tooltip\"]/button"));
//            assertNotNull(burgerMenu);
//
//            webDriver.get(Utils.BASE_URL);
//        });
//        drivers.forEach(WebDriver::quit);
//    }
//
//
//
//    @Test
//    void loginTest() {
//
//}
