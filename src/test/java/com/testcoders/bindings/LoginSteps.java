package com.testcoders.bindings;

import com.testcoders.pages.HomePage;
import com.testcoders.pages.LoginPage;
import com.testcoders.config.SpringConfiguration;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes= SpringConfiguration.class)
public class LoginSteps {
    @Autowired
    private HomePage homePage;
    @Autowired
    private LoginPage loginPage;

    public LoginSteps(HomePage homePage, LoginPage loginPage){
        this.homePage = homePage;
        this.loginPage = loginPage;
    }

    @Given("I navigate to the Login Page")
    public void i_navigate_to_the_Login_Page() {
        homePage.clickLoginLink();
    }

    @Given("I enter a valid username, ([^\"]*) and password, ([^\"]*)")
    public void i_enter_a_valid_username_and_password(String username, String password) {
    loginPage.setUsername(username);
    loginPage.setPassword(password);
    loginPage.clickSubmitButton();
    }

    @Then("I should be logged in")
    public void i_should_be_logged_in() {
        Assert.assertTrue(loginPage.isLoggedIn());
    }
}
