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
        WebElement deleteFriendButton = Utils.getElementBySelector(driver, By.xpath("//*[@id=\"content-column\"]/div/div[3]/div[1]/div/div/div/button"));
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

    public void confirmAddFriend() throws IllegalStateException{
        WebElement incomingRequestsButton = Utils.getElementBySelector(driver, By.xpath("//*[@id=\"people-list-header-menu-item-incoming-requests\"]"));
        incomingRequestsButton.click();
        WebElement confirmRequestButton = Utils.getElementBySelector(driver, By.xpath("//*[@id=\"content-column\"]/div/div[2]/div[1]/div/div/div/button"));
        if (confirmRequestButton != null)
            confirmRequestButton.click();
        else{
            throw new IllegalStateException("Невозможно согласовать заявку, заявки отсутсвуют");
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ignored){};
    }
}
