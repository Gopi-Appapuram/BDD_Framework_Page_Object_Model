Feature: Wishlist

    Scenario: Adding an item to the wishlist
        Given I am logged in as "Gopi"
        And I am on the product page for "Instant Pot Pressure Cooker"
        When I click on the "Add to Wishlist" button
        Then I should see a wishlist confirmation message
        And the item should be added to my wishlist

    Scenario: Removing an item from the wishlist
        Given I am logged in as "Gopi"
        And I navigate to my wishlist
        When I have products in my wishlist
        And I remove "Mr. Butler RoboChef" from the wishlist
        Then I should see a success message
        And "Mr. Butler RoboChef" should be removed from my wishlist
