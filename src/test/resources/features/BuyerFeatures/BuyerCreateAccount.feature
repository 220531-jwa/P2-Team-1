Feature: Creating buyer account
    
  Scenario: the User can create a new buyer account
  	Given a User is on the Create an Account page
    When the User enters a random username and password and name and chooses the Buyer option and clicks the Create Account button
    Then the Buyer will be redirected to the Login screen



