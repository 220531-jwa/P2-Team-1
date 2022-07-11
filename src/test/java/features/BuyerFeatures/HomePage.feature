Feature: Home Page Features

Scenario: Buyer is able to see a list of items.
	Given test buyer account is loaded
	And a buyer is on the home page
	When the buyer clicks the browse store button
	Then The buyer is redirected and can see the item catalogue
	And the products table is populated
	
Scenario: Buyer is able to add money to their account
	Given test buyer account is loaded
	And a buyer is on the home page
	When the buyer adds to their balance
	Then the new balance should be displayed

Scenario: Buyer is taken to supoprt ticket submission page
	Given test buyer account is loaded
	And a buyer is on the home page
	When the buyer presses submit support ticket
	Then buyer is redirected to submit ticket page

