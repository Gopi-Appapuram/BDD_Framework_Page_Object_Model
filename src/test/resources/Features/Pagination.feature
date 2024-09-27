Feature: Pagination Feature

    Scenario: Validate pagination controls on the page
        Given I am on the page with pagination
        When I load the pagination controls
        Then Validate pagination controls
