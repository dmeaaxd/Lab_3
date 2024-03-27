package org.example.uc;

import org.example.Utils;
import org.example.pages.MainPage;
import org.example.pages.MyFriendsPage;
import org.example.pages.UserPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class UserPageTest {
    @BeforeAll
    public static void init() {
        Utils.prepareDrivers();

        List<WebDriver> drivers = Utils.getDrivers();
        drivers.parallelStream().forEach(webDriver -> {
            MainPage mainPage = new MainPage(webDriver);
            webDriver.get(Utils.BASE_URL);
            mainPage.doFirstUserLogin();
            WebElement burgerMenu = Utils.getElementBySelector(webDriver, By.xpath("//*[@id=\"header-profile-tooltip\"]/button"));
            assertNotNull(burgerMenu);

            UserPage userPage = new UserPage(webDriver);
            webDriver.get(Utils.SECOND_USER_URL);

            try {
                userPage.deleteFriend();
            } catch (Exception ignored) {
            }
        });
        drivers.forEach(WebDriver::quit);
    }

    @Test
    void sendFriendRequestTest() {
        List<WebDriver> drivers = Utils.getDrivers();
        drivers.parallelStream().forEach(webDriver -> {
            MainPage mainPage = new MainPage(webDriver);
            webDriver.get(Utils.BASE_URL);
            mainPage.doFirstUserLogin();
            UserPage userPage = new UserPage(webDriver);
            webDriver.get(Utils.SECOND_USER_URL);
            userPage.addFriend();
            WebElement addFriendButton = Utils.getElementBySelector(webDriver, By.xpath("//*[@id=\"secondary-header\"]/div/div[3]/button"));
            String buttonText = addFriendButton.getText();
            assertTrue(buttonText.contains("Отменить"));
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
            try {
                userPage.addFriend();
                WebElement addFriendButton = Utils.getElementBySelector(webDriver, By.xpath("//*[@id=\"secondary-header\"]/div/div[3]/button"));
                String buttonText = addFriendButton.getText();
                assertTrue(buttonText.contains("Отменить"));
            } catch (IllegalStateException ignored){}
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
            myFriendsPage.confirmAddFriend();
            WebElement addFriendButton = Utils.getElementBySelector(webDriver, By.xpath("//*[@id=\"content-column\"]/div/div[2]/div[1]/div/div/div/button"));
            String buttonText = addFriendButton.getText();
            assertTrue(buttonText.contains("Не дружить"));
        });
        drivers.forEach(WebDriver::quit);
    }

    public static String generateString() {
        // Допустимые символы
        String allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        // Длина строки
        int length = 8;
        // Строка для хранения сгенерированной последовательности
        StringBuilder stringBuilder = new StringBuilder();
        // Инициализируем генератор случайных чисел
        Random random = new Random();

        // Генерируем каждый символ строки
        for (int i = 0; i < length; i++) {
            // Выбираем случайный индекс из диапазона допустимых символов
            int randomIndex = random.nextInt(allowedChars.length());
            // Добавляем выбранный символ к строке
            stringBuilder.append(allowedChars.charAt(randomIndex));
        }

        // Возвращаем сгенерированную строку
        return stringBuilder.toString();
    }

    @Test
    void writeOnBoard() {
        sendFriendRequest();
        confirmFriendRequest();
        List<WebDriver> drivers = Utils.getDrivers();
        drivers.parallelStream().forEach(webDriver -> {
            MainPage mainPage = new MainPage(webDriver);
            webDriver.get(Utils.BASE_URL);
            mainPage.doFirstUserLogin();
            UserPage userPage = new UserPage(webDriver);
            webDriver.get(Utils.SECOND_USER_URL);
            String message = generateString();
            userPage.writePost(message);
            WebElement lastPost = Utils.getElementBySelector(webDriver, By.xpath("//*[@id=\"content-column\"]/div/div/div[1]/div[2]/article/div[2]/div/div/div/div"));
            String lastPostText = lastPost.getText();
            assertEquals(message, lastPostText);
        });
        drivers.forEach(WebDriver::quit);
    }
}