package com.testcoders.pages;
import com.testcoders.utils.Logging;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public abstract class Page {
    protected static WebDriver driver;
    protected static WebDriverWait wait;
    protected Logging logging = new Logging();

    public abstract String toString();

    public static void setDriver(WebDriver driver) {
        Page.driver = driver;
    }

    public static void setWait(WebDriverWait wait) {
        Page.wait = wait;
    }

    public WebElement elementVisible(By identifier){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(identifier));
    }

    public WebElement elementPresent(By identifier){
        return wait.until(ExpectedConditions.presenceOfElementLocated(identifier));
    }

    public List<WebElement> allElementsVisible(By identifier){
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(identifier));
    }

    public List<WebElement> allElementsPresent(By identifier){
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(identifier));
    }
}
