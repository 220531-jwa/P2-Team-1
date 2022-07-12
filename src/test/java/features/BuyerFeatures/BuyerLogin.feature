#Feature: Logging in to a Buyer account
#  Background:
#    Given a Buyer is on the Login screen and has a valid account
#  Scenario Outline: the Buyer can login to their account
#    When the Buyer enters their "<username>" and "<password>" and clicks the Login button
#    Then the Buyer reaches the Buyer homepage
#
#    Examples:
#    |username|password|
#    |kc1     |kcpass  |
#    |apple   |bottom  |
