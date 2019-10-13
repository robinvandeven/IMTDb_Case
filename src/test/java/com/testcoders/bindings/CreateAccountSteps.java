package com.testcoders.bindings;

import com.testcoders.pages.HomePage;
import com.testcoders.pages.SignUpPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class CreateAccountSteps {
    @Autowired
    HomePage homePage;
    @Autowired
    SignUpPage signUpPage;

    public CreateAccountSteps (HomePage homePage, SignUpPage signUpPage){
        this.homePage = homePage;
        this.signUpPage = signUpPage;
    }

    @Given("I navigate to the Sign Up page")
    public void i_navigate_to_the_Sign_Up_page() {
        homePage.clickSignUpLink();
    }

    @Given("I enter a unique username and password")
    public void i_enter_a_unique_username_and_password(List<List<String>> credentials) {
    signUpPage.setUsername(credentials.get(1).get(0));
    signUpPage.setPassword(credentials.get(1).get(1));
    signUpPage.setRepeatPassword(credentials.get(1).get(1));
    signUpPage.clickSignUpButton();
    }

    @Then("A new account should be created")
    public void a_new_account_should_be_created() {
        Assert.assertTrue(signUpPage.isUserCreated());
    }


}
