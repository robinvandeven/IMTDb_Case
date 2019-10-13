package com.testcoders.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

@Component
public class SignUpPage extends Page{

    @Override
    public String toString() {
        return "Sign Up Page";
    }

    public void setUsername(String username){
        WebElement usernameInputBox = elementVisible(By.id("username"));
        usernameInputBox.sendKeys(username);
        logging.logger.info(getClass() + ", Username set: " + username);
    }

    public void setPassword(String password){
        WebElement passwordInputBox = elementVisible(By.id("password"));
        passwordInputBox.sendKeys(password);
        logging.logger.info(getClass() + ", Password set: " + password);
    }

    public void setRepeatPassword(String password){
        WebElement repeatPasswordInputBox = elementVisible(By.id("repeat-password"));
        repeatPasswordInputBox.sendKeys(password);
        logging.logger.info(getClass() + ",  Repeat password set: " + password);
    }

    public void clickSignUpButton(){
        WebElement signUpButton = elementVisible(By.xpath("//button[text()=\"Sign Up\n" +
                "      \"]"));
        if (signUpButton.isEnabled()){
            signUpButton.click();
            logging.logger.info(getClass() + ", Sign Up button clicked");
        }
        else {
            logging.logger.error(getClass() + ", Sign Up button NOT enabled");
        }
    }

    public boolean isUserCreated(){
        try{
            WebElement createdUser = elementVisible(By.xpath("//*[text()=\"Created user\"]"));
            logging.logger.info("User created successfully");
            return true;
        }
        catch (TimeoutException e){
            logging.logger.error("Failed to create user. " + e.getMessage());
            return false;
        }
    }

}
