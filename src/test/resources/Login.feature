Feature: Login Page

  Background:
    Given I navigate to the Login Page

  Scenario Outline: Log in
    And I enter a valid username, <Username> and password, <Password>
    Then I should be logged in

    Examples:
    |Username      |Password |
    |testadmin     |admin     |
