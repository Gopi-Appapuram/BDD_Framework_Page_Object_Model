Feature: Searching for Products

  Scenario: Searching for a product by name
    Given I opened chrome browser
    When I am on the Amazon homepage
    Given I enter "iPhone 13" into the search bar
    When I click on the search button
    Then I should see a list of search results for "iPhone 13"

  Scenario: Filtering search results
    Given I have searched for "laptop"
    When I click on the search button
    And I apply the min "1000" to max "2000" filter
    Then I should see only laptops priced under "2000" in the search results
