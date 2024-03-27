package org.example.pages;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;


public abstract class Page {

    @Getter
    @Setter
    protected WebDriver driver;

    public Page(WebDriver driver) {
        this.driver = driver;
    }
}