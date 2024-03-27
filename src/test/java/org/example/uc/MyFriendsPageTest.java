package org.example.uc;

import org.example.Utils;
import org.example.pages.MainPage;
import org.example.pages.MyFriendsPage;
import org.example.pages.UserPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MyFriendsPageTest {

    @BeforeAll
    public static void prepareDrivers() {
        Utils.prepareDrivers();
    }

    @Test
    void confirmFriendRequestTest() {
        sendFriendRequest();
        List<WebDriver> drivers = Utils.getDrivers();
        drivers.parallelStream().forEach(webDriver -> {
            MainPage mainPage = new MainPage(webDriver);
            webDriver.get(Utils.BASE_URL);
            mainPage.doSecondUserLogin();
            WebElement burgerMenuButton = Utils.getElementBySelector(webDriver, By.xpath("//*[@id=\"header-profile-tooltip\"]/button"));
            burgerMenuButton.click();
            WebElement myFriendsButton = Utils.getElementBySelector(webDriver, By.xpath("/html/body/div[3]/div/div/div/a[7]"));
            myFriendsButton.click();
            MyFriendsPage myFriendsPage = new MyFriendsPage(webDriver);
            myFriendsPage.confirmAddFriend();
            WebElement addFriendButton = Utils.getElementBySelector(webDriver, By.xpath("//*[@id=\"content-column\"]/div/div[2]/div[1]/div/div/div/button"));
            String buttonText = addFriendButton.getText();
            assertTrue(buttonText.contains("Не дружить"));
        });
        drivers.forEach(WebDriver::quit);
    }

    @Test
    public void deleteFriendTest() {
        sendFriendRequest();
        confirmFriendRequest();
        List<WebDriver> drivers = Utils.getDrivers();
        drivers.parallelStream().forEach(webDriver -> {
            MainPage mainPage = new MainPage(webDriver);
            webDriver.get(Utils.BASE_URL);
            mainPage.doFirstUserLogin();
            WebElement burgerMenuButton = Utils.getElementBySelector(webDriver, By.xpath("//*[@id=\"header-profile-tooltip\"]/button"));
            burgerMenuButton.click();
            WebElement myFriendsButton = Utils.getElementBySelector(webDriver, By.xpath("/html/body/div[3]/div/div/div/a[7]"));
            myFriendsButton.click();
            MyFriendsPage myFriendsPage = new MyFriendsPage(webDriver);
            myFriendsPage.deleteFriend();
            WebElement deleteFriendButton = Utils.getElementBySelector(webDriver, By.xpath("//*[@id=\"content-column\"]/div/div[3]/div[1]/div/div/div/button"));
            String buttonText = deleteFriendButton.getText();
            assertTrue(buttonText.contains("Дружить"));
        });
        drivers.forEach(WebDriver::quit);
    }

    void sendFriendRequest() {
        List<WebDriver> drivers = Utils.getDrivers();
        drivers.parallelStream().forEach(webDriver -> {
            MainPage mainPage = new MainPage(webDriver);
            webDriver.get(Utils.BASE_URL);
            mainPage.doFirstUserLogin();
            Utils.waitUntilPageLoads(webDriver,10);
            UserPage userPage = new UserPage(webDriver);
            webDriver.get(Utils.SECOND_USER_URL);
            Utils.waitUntilPageLoads(webDriver,10);

            WebElement button = Utils.getElementBySelector(webDriver, By.xpath("//*[@id=\"secondary-header\"]/div/div[3]/button"));
            String buttonText = button.getText();
            if (buttonText.contains("Не дружить")){
                button.click();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ignored){}
            }
            if (!buttonText.contains("Отменить")){
                userPage.addFriend();
                WebElement addFriendButton = Utils.getElementBySelector(webDriver, By.xpath("//*[@id=\"secondary-header\"]/div/div[3]/button"));
                buttonText = addFriendButton.getText();
            }
            assertTrue(buttonText.contains("Отменить"));
        });
        drivers.forEach(WebDriver::quit);
    }

    void confirmFriendRequest() {
        List<WebDriver> drivers = Utils.getDrivers();
        drivers.parallelStream().forEach(webDriver -> {
            MainPage mainPage = new MainPage(webDriver);
            webDriver.get(Utils.BASE_URL);
            mainPage.doSecondUserLogin();
            WebElement burgerMenuButton = Utils.getElementBySelector(webDriver, By.xpath("//*[@id=\"header-profile-tooltip\"]/button"));
            burgerMenuButton.click();
            WebElement myFriendsButton = Utils.getElementBySelector(webDriver, By.xpath("/html/body/div[3]/div/div/div/a[7]"));
            myFriendsButton.click();
            MyFriendsPage myFriendsPage = new MyFriendsPage(webDriver);
            try {
                myFriendsPage.confirmAddFriend();
                WebElement addFriendButton = Utils.getElementBySelector(webDriver, By.xpath("//*[@id=\"content-column\"]/div/div[2]/div[1]/div/div/div/button"));
                String buttonText = addFriendButton.getText();
                assertTrue(buttonText.contains("Не дружить"));
            } catch (IllegalStateException ignored){}

        });
        drivers.forEach(WebDriver::quit);
    }
}
