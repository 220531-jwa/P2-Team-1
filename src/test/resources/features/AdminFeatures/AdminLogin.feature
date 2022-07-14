Feature: Logging in to an Admin account
  Background:
    Given an Admin is on the Login screen and has a valid account
  Scenario Outline: the Admin can login to their account
    When the Admin enters their "<username>" and "<password>" and clicks the Login button
    Then the Admin reaches the Admin homepage

    Examples:
      |username|password|
      |admin   |admin   |
