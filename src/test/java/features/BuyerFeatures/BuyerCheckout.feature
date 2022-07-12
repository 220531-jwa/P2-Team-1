#Feature: checking out
#  Background:
#    Given a Buyer is on their Cart page with at least one item in the Cart
#  Scenario: the Buyer can check out
#    When the Buyer clicks the Checkout button
#    Then the Cart Total will be removed from the Buyer's balance and they will see the Checkout message