Feature: Checking Out

    Scenario: Proceeding to checkout with items in the cart
        Given I have "Macbook Pro" and "Wireless Mouse" in my cart
        When I click on the cart icon
        And I click on the "Proceed to Checkout" button
        Then I should see the checkout page with my items listed
        And I should be able to enter my shipping and payment details
        And complete the checkout process successfully
