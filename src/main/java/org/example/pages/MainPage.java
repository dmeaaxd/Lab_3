package org.example.pages;

import org.example.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebElement;

public class MainPage extends Page{

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void doLogin() {
        tryLogin(Utils.CORRECT_LOGIN, Utils.CORRECT_PASSWORD);
    }

    public void doWrongLogin() {
        tryLogin(Utils.CORRECT_LOGIN, Utils.WRONG_PASSWORD);
    }

    public void doLogout() {
        WebElement burgerMenuButton = Utils.getElementBySelector(driver, By.xpath(""));
        burgerMenuButton.click();
        WebElement logoutButton = Utils.getElementBySelector(driver, By.xpath(""));
        logoutButton.click();
        WebElement confirmationLogoutButton = Utils.getElementBySelector(driver, By.xpath(""));
        logoutButton.click();
    }

    private void tryLogin(CharSequence login, CharSequence password) {
        WebElement loginButton = Utils.getElementBySelector(driver, By.xpath(""));
        loginButton.click();
        WebElement loginByEmailButton = Utils.getElementBySelector(driver, By.xpath(""));
        loginByEmailButton.click();
        WebElement loginInput = Utils.getElementBySelector(driver, By.xpath(""));
        WebElement loginPassword = Utils.getElementBySelector(driver, By.xpath(""));
        WebElement authButton = Utils.getElementBySelector(driver, By.xpath(""));
        loginInput.clear();
        loginPassword.clear();
        loginInput.sendKeys(login);
        loginPassword.sendKeys(password);
        authButton.click();
    }
}
