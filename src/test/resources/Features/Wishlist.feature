Feature: Wishlist

    Scenario: Adding an item to the wishlist
        Given I am logged in as "John Doe"
        And I am on the product page for "Instant Pot Pressure Cooker"
        When I click on the "Add to Wishlist" button
        Then I should see a confirmation message
        And the item "Instant Pot Pressure Cooker" should be added to my wishlist

    Scenario: Removing an item from the wishlist
        Given I am logged in as "John Doe"
        And I have "Echo Dot" in my wishlist
        When I navigate to my wishlist
        And I remove "Echo Dot" from the wishlist
        Then I should see a success message
        And "Echo Dot" should be removed from my wishlist
