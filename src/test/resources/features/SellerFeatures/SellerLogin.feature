Feature: Logging in to a Seller account
  Background:
    Given a Seller is on the Login screen and has a valid account
  Scenario Outline: the Seller can login to their account
    When the Seller enters their "<username>" and "<password>" and clicks the Login button
    Then the Seller reaches the Seller homepage

    Examples:
      |username  |password|
      |tinendo   |tinendo |