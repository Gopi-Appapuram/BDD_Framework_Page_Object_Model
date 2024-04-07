Feature: User Login

  Scenario: Logging in with valid credentials
    Given I am on the Amazon login page
    When I enter my email address "appapuramgopi6@gmail.com" on the login page
    And I click on the Continue button on the login page
    When I enter my password "Gopi@1999" on the login page
    And I click on the Sign-In button on the login page
    Then I should be logged in successfully

  Scenario: Logging in with incorrect credentials
    Given I am on the Amazon login page
    When I enter my email address "appapuramgopi6@gmail.in" on the login page
    And I click on the Continue button on the login page
    Then I should see an error message on the login page
    And I should remain on the login page
