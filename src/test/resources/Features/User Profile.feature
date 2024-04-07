Feature: User Profile

    Scenario: Viewing user profile information
        Given I am logged in as "Jane Smith"
        When I navigate to the "My Account" section
        Then I should see my profile information such as name, email, and address

    Scenario: Updating user profile information
        Given I am logged in as "Jane Smith"
        When I navigate to the "My Account" section
        And I update my name to "Jane Doe"
        And I save the changes
        Then I should see a success message
        And my profile information should be updated to "Jane Doe"
