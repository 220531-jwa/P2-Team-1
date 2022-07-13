Feature: Creating buyer account
    Given a User is on the Create an Account page
  Scenario: the User can create a new buyer account
    When the User enters a random username and password and name and chooses the Buyer option and clicks the Create Account button
    Then the Buyer will be redirected to the Login screen


