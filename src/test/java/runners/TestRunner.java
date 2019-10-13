package runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"html:reports/cucumber-html-report",
                "html:target/selenium-reports",
                "json:reports/cucumber.json",
                "pretty"},
        tags = {""},
        features = {"src/test/resources"},
        glue = {"com/testcoders/bindings"}
)
//@ContextConfiguration(classes= SpringConfiguration.class)


public class TestRunner {



}