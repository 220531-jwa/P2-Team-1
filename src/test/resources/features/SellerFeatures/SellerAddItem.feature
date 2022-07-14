Feature: Add an item
  Scenario Outline: The Seller creates an item
    Given a Seller is logged in and is on the Add an Item page
    When the Seller enters the "<name>", "<description>", "<cost>", and "<inventory>" and clicks Submit
    Then the Seller will see the Item Created message
    And the seller will delete the made item for good measure

    Examples:
    |name        |description               |cost |inventory|
    |Test Game Creation|This is a test      |72.82|15        |