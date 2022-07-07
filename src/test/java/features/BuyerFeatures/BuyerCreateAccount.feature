Feature: Creating buyer account
  Background:
    Given a User is on the Create an Account page
  Scenario Outline: the User can create a new buyer account
    When the User enters their "<username>", "<password>", and "<name>" and clicks the Create Account button
    Then the User will be redirected to the Login screen

    Examples:
    |username|password|name        |
    |dorothy |gale    |Dorothy Gale|
