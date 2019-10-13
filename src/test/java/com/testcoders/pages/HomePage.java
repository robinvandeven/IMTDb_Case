package com.testcoders.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.stereotype.Component;

@Component
public class HomePage extends Page{

    @Override
    public String toString() {
        return "Home Page";
    }

    public void clickSignUpLink(){
        WebElement signUpLink = elementVisible(By.xpath("//*[text()=\"Sign Up\"]"));
        signUpLink.click();
        logging.logger.info(toString() + ", Sign Up link clicked");
    }

    public void clickLoginLink(){
        WebElement loginLink = elementVisible(By.xpath("//*[text()=\"Login\"]"));
        loginLink.click();
        logging.logger.info(toString() + ", Login link clicked");
    }

    public void clickListAllMoviesButton(){
        WebElement listAllMoviesButton = elementVisible(By.xpath("//button[text()=\"List all movies\"]"));
        if (listAllMoviesButton.isEnabled()){
            listAllMoviesButton.click();
            logging.logger.info(toString() + ", List All Movies button clicked");
        }
        else{
            logging.logger.error(toString() + ", List All Movies button NOT enabled");
        }
    }

    public void clickPlayTrailerButton(){
        WebElement trailerIframe = elementVisible(By.xpath("//iframe[contains(@src, \"https://www.youtube.com/embed\")]"));
        driver.switchTo().frame(trailerIframe);
        WebElement playButton = elementPresent(By.xpath("//div[@class=\"ytp-cued-thumbnail-overlay\"]//button[contains(@aria-label, \"Play\")]"));
        playButton.click();
        logging.logger.info("Play button clicked");
    }

    public void playTrailerFor (int secondsToPlay){
        logging.logger.info("Trailer will play for " + secondsToPlay + " seconds");
        WebElement progressBar = elementPresent(By.xpath("//*[contains (@class, \"ytp-chrome-bottom\")]//div[contains(@class, \"ytp-progress-bar \")]"));
        int secondsPlayed = Integer.parseInt(progressBar.getAttribute("aria-valuenow"));
        while (secondsPlayed <= secondsToPlay){
            secondsPlayed = Integer.parseInt(progressBar.getAttribute("aria-valuenow"));
        }
    }

    public boolean onHomePage(){
        try{
            WebElement homePageDiv = elementVisible(By.className("home"));
            logging.logger.info("On homepage");
            return true;
        }
        catch (TimeoutException e){
            logging.logger.error("NOT on homepage");
            return false;
        }
    }
}
