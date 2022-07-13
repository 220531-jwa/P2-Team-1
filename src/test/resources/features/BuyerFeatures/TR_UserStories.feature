#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
Feature: User Story Tests by Trevor Rowand

Scenario: Buyer can add an item to their cart
     Given the test buyer account is loaded
     And a Buyer is on the ItemPage
     When the Buyer clicks the CartButton
     Then The Element should be added to the Cart

Scenario: A Buyer can remove an Item from their Cart
     Given the test buyer account is loeaded
     And the buyer is on the CartListPage
     When the Buyer clicks on the RemoveCartButton by an Item
     Then the Item should be removed from the Buyer Cart