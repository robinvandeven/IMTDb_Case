package com.testcoders.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;

@Component
public class DriverUtils {
    private static WebDriver driver;
    private int timoutInSeconds = 7;
    private int sleepInMilis = 20;

    @Autowired
    private Logging logging;

    public DriverUtils(Logging logging){
        this.logging = logging;
    }

    public void initializeDriverContainer(DriverType browserName){
            try {
                switch (browserName){
                    case CHROME:
//                        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), new ChromeOptions());
                        System.setProperty("webdriver.chrome.driver", "C:/Selenium Drivers/chromedriver_win32/chromedriver.exe");
                        driver = new ChromeDriver();
                        break;
                    case FIREFOX:
//                        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), new FirefoxOptions());
                        System.setProperty("webdriver.gecko.driver", "C:/Selenium Drivers/geckodriver-v0.26.0-win64/geckodriver.exe");
                        driver = new FirefoxDriver();
                        break;
                    default:
//                        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), new ChromeOptions());
                        System.setProperty("webdriver.chrome.driver", "C:/Selenium Drivers/chromedriver_win32/chromedriver.exe");
                        driver = new ChromeDriver();
                        break;
                }
                driver.manage().window().maximize();
                logging.logger.info("Driver initialized, browser used: " + browserName);
            } catch (Exception e) {
                logging.logger.error("Failed to initialize driver. " + e.getMessage());
                e.printStackTrace();
            }
        }

    public WebDriver getDriver() {
            return driver;
    }

    public void quitDriver(){
        if (driver == null) {
            logging.logger.error("No driver initialized");
        }
        else {
            driver.quit();
        }
    }

    public WebDriverWait getWebDriverWait(){
        return new WebDriverWait(driver, timoutInSeconds, sleepInMilis);
    }

    public void setSleepInMilis(int sleepInMilis) {
        this.sleepInMilis = sleepInMilis;
    }

    public void setTimoutInSeconds(int timoutInSeconds){
        this.timoutInSeconds = timoutInSeconds;
    }
}
