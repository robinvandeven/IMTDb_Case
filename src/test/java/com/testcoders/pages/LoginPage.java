package com.testcoders.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

@Component
public class LoginPage extends Page{

    @Override
    public String toString() {
        return "Login Page";
    }

    public void setUsername(String username){
        WebElement usernameInputBox = elementVisible(By.id("username"));
        usernameInputBox.sendKeys(username);
        logging.logger.info(toString() + ", Username set: " + username);
    }

    public void setPassword(String password){
        WebElement passwordInputBox = elementVisible(By.id("password"));
        passwordInputBox.sendKeys(password);
        logging.logger.info(toString() + ", Password set: " + password);
    }

    public void clickSubmitButton(){
        WebElement submitButton = elementVisible(By.className("login__submit"));
        if (submitButton.isEnabled()){
            submitButton.click();
            logging.logger.info(toString() + " Submit button clicked");
        }
        else {
            logging.logger.error(toString() + " , Submit button NOT clicked");
        }
    }

    public boolean isLoggedIn(){
        try {
            WebElement logOutLink = elementVisible(By.xpath("//*[text()=\"Logout\"]"));
            logging.logger.info("Successfully logged in");
            return true;
        }
        catch (TimeoutException e){
            logging.logger.error("Failed to log in");
            return false;
        }
    }
}


