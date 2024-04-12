Feature: Deleting items from the shopping cart
    I want to be able to delete items from my shopping cart,
    So that I can modify my purchase before checking out.

    Scenario: Delete a single item from the cart
        Given I have items in my shopping cart
        When I select to delete any one item
        Then the item should be removed from the cart

    Scenario: Delete multiple items from the cart
        Given I have items in my shopping cart
        When I select to delete items any 2 items
        Then the items should be removed from the cart

    Scenario: Deleting all items from the cart
        Given I have items in my shopping cart
        When I select to delete all items
        Then the cart should be empty
