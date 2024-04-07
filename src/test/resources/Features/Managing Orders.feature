Feature: Managing Orders

    Scenario: Viewing order history
        Given I am logged in as a registered user
        When I navigate to the "Your Orders" section
        Then I should see a list of my previous orders
        And I should be able to click on an order to view its details

    Scenario: Cancelling an order
        Given I have an order with the status "Pending"
        When I go to the order details page
        And I click on the "Cancel Order" button
        Then the status of the order should change to "Cancelled"
