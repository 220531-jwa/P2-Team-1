Feature: checking out
  Background:
    Given a Buyer is logged in and has items in their cart and is on the cart page
  Scenario: the Buyer can check out
    When the Buyer clicks the Checkout button
    Then the Cart Total will be removed from the Buyer's balance and they will see the Checkout message