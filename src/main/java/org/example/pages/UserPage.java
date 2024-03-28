package org.example.pages;

import org.example.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UserPage extends Page {
    public UserPage(WebDriver driver) {
        super(driver);
    }

    public void addFriend() throws IllegalStateException{
        WebElement addFriendButton = Utils.getElementBySelector(driver, By.xpath("//*[@id=\"secondary-header\"]/div/div[3]/button"));
        String buttonText = addFriendButton.getText();
        if (buttonText.contains("Дружить")) {
            addFriendButton.click();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ignored){}
        } else {
            throw new IllegalStateException("Невозможно добавить друга, кнопка \\\"Дружить\\\" отсутсвует");
        }

    }

    public void deleteFriend() throws IllegalStateException{
        WebElement deleteFriendButton = Utils.getElementBySelector(driver, By.xpath("//*[@id=\"secondary-header\"]/div/div[3]/button"));
        String buttonText = deleteFriendButton.getText();
        if (buttonText.contains("Не дружить") || buttonText.contains("Отменить")) {
            deleteFriendButton.click();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ignored){}
        } else {
            throw new IllegalStateException("Невозможно удалить друга, кнопка \\\"Не дружить\\\" отсутсвует");
        }
    }

    public void writePost(String message) {
        WebElement postButton = Utils.getElementBySelector(driver, By.xpath("//*[@id=\"content-column\"]/div/div/div[1]/div[1]/div/div[1]/div/button"));
        postButton.click();
        WebElement postInput = Utils.getElementBySelector(driver, By.xpath("/html/body/div[3]/div/div/div/div/div[2]/div[1]/div/div/div/div/div[1]"));
        postInput.clear();
        postInput.click();
        for (int i = 0; i < message.length(); i++) postInput.sendKeys(String.valueOf(message.charAt(i)));
        WebElement sendButton = Utils.getElementBySelector(driver, By.xpath("/html/body/div[3]/div/div/div/div/div[3]/div/button"));
        sendButton.click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ignored){};
    }
}
