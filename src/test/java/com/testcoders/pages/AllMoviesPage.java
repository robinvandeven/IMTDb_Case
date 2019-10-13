package com.testcoders.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class AllMoviesPage extends Page{

    @Override
    public String toString() {
        return "All Movies Page";
    }

    public void searchForMovie(String title){
        WebElement searchInputBox = elementVisible(By.className("search-movie__input"));
        searchInputBox.sendKeys(title);
        WebElement searchButton = elementVisible(By.className("search-movie__button"));
        searchButton.click();
        logging.logger.info(toString() + " , searching for " + title);
    }

    public void printSearchResults(){
        try{
            List<WebElement> searchResults = allElementsVisible(By.xpath("//*[@class=\"movie__details\"]//h3"));
            for (WebElement result : searchResults){
                logging.logger.info(result.getText());
            }
        }
        catch (TimeoutException e){
            logging.logger.error("No search results! " + e.getMessage());
            }
    }
}

