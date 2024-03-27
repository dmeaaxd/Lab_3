package org.example.pages;

import org.example.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UserPage extends Page {
    public UserPage(WebDriver driver) {
        super(driver);
    }

    public void addFriend() {
        WebElement addFriendButton = Utils.getElementBySelector(driver, By.xpath("//*[@id=\"secondary-header\"]/div/div[3]/button"));
        String buttonText = addFriendButton.getText();
        if (buttonText.contains("Не дружить")) {
            addFriendButton.click();
        } else {
            throw new IllegalStateException("Невозможно добавить друга, кнопка \\\"Дружить\\\" отсутсвует");
        }

    }

    public void deleteFriend() {
        WebElement deleteFriendButton = Utils.getElementBySelector(driver, By.xpath("//*[@id=\"secondary-header\"]/div/div[3]/button"));
        String buttonText = deleteFriendButton.getText();
        if (buttonText.contains("Не дружить")) {
            deleteFriendButton.click();
        } else {
            throw new IllegalStateException("Невозможно удалить друга, кнопка \\\"Не дружить\\\" отсутсвует");
        }
    }

    public void writePost() {
        WebElement postButton = Utils.getElementBySelector(driver, By.xpath("//*[@id=\"content-column\"]/div/div/div[1]/div[1]/div/div[1]/div/button"));
        postButton.click();
        WebElement postInput = Utils.getElementBySelector(driver, By.xpath("/html/body/div[3]/div/div/div/div/div[2]/div[1]/div/div/div/div/div[1]"));
        postInput.clear();
        postInput.sendKeys("SELENIUM TEST");
        WebElement sendButton = Utils.getElementBySelector(driver, By.xpath("/html/body/div[3]/div/div/div/div/div[3]/div/button"));
        sendButton.click();
    }
}
