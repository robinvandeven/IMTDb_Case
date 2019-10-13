package com.testcoders.bindings;

import com.testcoders.pages.HomePage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PlayTrailerSteps {
    @Autowired
    HomePage homePage;

    public PlayTrailerSteps (HomePage homePage){
        this.homePage = homePage;
    }

    @Given("I am on the homepage")
    public void i_am_on_the_homepage() {
        Assert.assertTrue(homePage.onHomePage());
    }

    @Given("I play the video trailer")
    public void i_play_the_video_trailer() {
        homePage.clickPlayTrailerButton();
    }

    @Then("I want to play it")
    public void i_want_to_play_it(List<List<String>> seconds) {
        int secondsToPlay = Integer.parseInt(seconds.get(1).get(0));
        homePage.playTrailerFor(secondsToPlay);
    }
}
