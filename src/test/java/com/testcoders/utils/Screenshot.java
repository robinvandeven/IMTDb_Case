package com.testcoders.utils;

import cucumber.api.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static junit.framework.TestCase.fail;

@Component
public class Screenshot {
    private Logging logging = new Logging();

    public Screenshot(Logging logging){
        this.logging = logging;
    }

    public void saveScreenShotPNG(WebDriver driver){
        try{
            driver = new Augmenter().augment( driver );
            TakesScreenshot takesScreenshot = ((TakesScreenshot)driver);
            File screenshot =  takesScreenshot.getScreenshotAs(OutputType.FILE);
            String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
            FileUtils.copyFile(screenshot, new File("C:/temp/SeleniumScreenshots/screenshot"+ timeStamp +".png"));
            logging.logger.info("Screenshot was taken");
        }
        catch (Exception e){
            e.printStackTrace();
            logging.logger.error("Failed to take screenshot. " + e.getMessage());
        }
    }

    public void saveScreenShotBytes(WebDriver driver, Scenario scenario){
        try{
            driver = new Augmenter().augment( driver );
            TakesScreenshot takesScreenshot = ((TakesScreenshot)driver);
            byte[] screenshot =  takesScreenshot.getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
            logging.logger.info("Screenshot was attached to scenario report");
        }
        catch (Exception e){
            e.printStackTrace();
            logging.logger.error("Failed to take screenshot. " + e.getMessage());
        }
    }
}
