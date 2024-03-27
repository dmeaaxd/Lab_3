package org.example.pages;

import org.example.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage extends Page {

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
        WebElement burgerMenuButton = Utils.getElementBySelector(driver, By.xpath("//*[@id=\"header-profile-tooltip\"]/button"));
        burgerMenuButton.click();
        WebElement logoutButton = Utils.getElementBySelector(driver, By.xpath("/html/body/div[3]/div/div/div/a[13]"));
        logoutButton.click();
        WebElement confirmLogoutButton = Utils.getElementBySelector(driver, By.xpath("/html/body/div[3]/div/div/div/div/button"));
        confirmLogoutButton.click();
    }

    private void tryLogin(CharSequence login, CharSequence password) {
        WebElement loginButton = Utils.getElementBySelector(driver, By.xpath("//*[@id=\"header\"]/div/div/div/div/div[4]/ul/li[2]/button"));
        loginButton.click();
        WebElement loginByEmailButton = Utils.getElementBySelector(driver, By.xpath("/html/body/div[3]/div/div/div/div/div/form/div[6]/button"));
        loginByEmailButton.click();
        WebElement loginInput = Utils.getElementBySelector(driver, By.xpath("//*[@id=\"authFormLoginByEmailEmail\"]"));
        WebElement loginPassword = Utils.getElementBySelector(driver, By.xpath("//*[@id=\"authFormLoginByEmailPassword\"]"));
        WebElement authButton = Utils.getElementBySelector(driver, By.xpath("/html/body/div[3]/div/div/div/div/div/form/div[3]/button"));
        loginInput.clear();
        loginPassword.clear();
        loginInput.sendKeys(login);
        loginPassword.sendKeys(password);
        authButton.click();
    }
}
