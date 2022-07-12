Feature: Creating buyer account

Scenario: Create account feature works
    Given a User is on the Create an Account page
    When the User enters a random username and password and name and presses create account
    Then the User will be redirected to the Login screen
