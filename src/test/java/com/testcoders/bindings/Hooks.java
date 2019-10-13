package com.testcoders.bindings;

import com.testcoders.config.SpringConfiguration;
import com.testcoders.pages.Page;
import com.testcoders.utils.*;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import java.net.MalformedURLException;
import java.net.URL;

@ContextConfiguration(classes= SpringConfiguration.class)
public class Hooks {
    @Autowired
    private Logging logging;
    @Autowired
    private DriverUtils driverUtils;
    @Autowired
    private Screenshot screenshot;

    public Hooks(Logging logging, DriverUtils driverUtils, Screenshot screenshot){
        this.logging = logging;
        this.driverUtils = driverUtils;
        this.screenshot = screenshot;
    }

    @Before
    public void initializeDriver(Scenario scenario) throws MalformedURLException{
        logging.logger.info("Scenario being executed: " + scenario.getName());
        driverUtils.initializeDriverContainer(DriverType.FIREFOX);
        Page.setDriver(driverUtils.getDriver());
        Page.setWait(driverUtils.getWebDriverWait());
        driverUtils.getDriver().navigate().to(new URL("http://localhost:80"));
    }

    @After
    public void closeDriver(Scenario scenario) {
        if (scenario.isFailed()) {
            logging.logger.error("Scenario failed: " + scenario.getName());
            screenshot.saveScreenShotBytes(driverUtils.getDriver(), scenario);
            screenshot.saveScreenShotPNG(driverUtils.getDriver());
        }
        else {
            logging.logger.info("Scenario completed: " + scenario.getName());
        }
        driverUtils.quitDriver();
        logging.logger.info("Driver closed");
    }
}
