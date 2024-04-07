Feature: Adding Items to Cart

  Scenario: Adding a single item to the cart
    Given I am on the product page for "Nintendo Switch"
    When I click on the "Add to Cart" button
    Then I should see a confirmation message
    And the item "Nintendo Switch" should be added to my cart

  Scenario: Adding multiple items to the cart
    Given I have "Apple AirPods,Sony Headphones" in my cart
    When I increase the quantity of "Apple AirPods" to 2
    Then I should see 2 "Apple AirPods" in my cart
    And the total price should reflect the updated quantity
