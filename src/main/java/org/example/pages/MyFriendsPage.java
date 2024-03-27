package org.example.pages;

import org.example.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MyFriendsPage extends Page {

    public MyFriendsPage(WebDriver driver) {
        super(driver);
    }

    public void deleteFriend() {
        WebElement buttonText = Utils.getElementBySelector(driver, By.xpath("//*[@id=\"content-column\"]/div/div[3]/div[1]/div/div/div/button/text()"));
        if (!buttonText.getText().contains("Не дружить")) {
            throw new IllegalStateException("Невозможно удалить друга, кнопка \"Не дружить\" отсутсвует");
        }
        WebElement deleteFriendButton = Utils.getElementBySelector(driver, By.xpath("//*[@id=\"content-column\"]/div/div[3]/div[1]/div/div/div/button"));
        deleteFriendButton.click();
    }

    public void addFriend() throws IllegalStateException{
        WebElement buttonText = Utils.getElementBySelector(driver, By.xpath("//*[@id=\"content-column\"]/div/div[3]/div[1]/div/div/div/button/text()"));
        if (!buttonText.getText().contains("Дружить")) {
            throw new IllegalStateException("Невозможно добавить друга, кнопка \"Дружить\" отсутсвует");
        }
        WebElement addFriendButton = Utils.getElementBySelector(driver, By.xpath("//*[@id=\"content-column\"]/div/div[3]/div[1]/div/div/div/button"));
        addFriendButton.click();
    }

    public void confirmAddFriend() {
        WebElement incomingRequestsButton = Utils.getElementBySelector(driver, By.xpath("//*[@id=\"people-list-header-menu-item-incoming-requests\"]"));
        incomingRequestsButton.click();
        WebElement confirmRequestButton = Utils.getElementBySelector(driver, By.xpath("//*[@id=\"content-column\"]/div/div[2]/div[1]/div/div/div/button"));
        confirmRequestButton.click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ignored){};
    }
}
