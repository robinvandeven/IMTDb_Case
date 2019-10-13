package com.testcoders.bindings;

import com.testcoders.pages.AllMoviesPage;
import com.testcoders.pages.HomePage;
import com.testcoders.pages.LoginPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class SearchMovieSteps {
    @Autowired
    HomePage homePage;
    @Autowired
    LoginPage loginPage;
    @Autowired
    AllMoviesPage allMoviesPage;

    public SearchMovieSteps(HomePage homePage, LoginPage loginPage, AllMoviesPage allMoviesPage){
        this.homePage = homePage;
        this.loginPage = loginPage;
        this.allMoviesPage = allMoviesPage;
    }

    @Given("I am logged in")
    public void i_am_logged_in(List<List<String>> credentials) {
        homePage.clickLoginLink();
        loginPage.setUsername(credentials.get(1).get(0));
        loginPage.setPassword(credentials.get(1).get(1));
        loginPage.clickSubmitButton();
    }

    @Then("I want to search for the movie ([^\"]*)")
    public void i_want_to_search_for_the_movie(String title) {
        allMoviesPage.searchForMovie(title);
    }

    @Then("I want to list all search results")
    public void i_want_to_list_all_search_results(){
        allMoviesPage.printSearchResults();
    }
}
