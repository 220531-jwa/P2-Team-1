Feature: Product List Feature

Scenario: Buyer is able to see a list of items.
	Given test buyer account is loaded
	And a buyer is on the home page
	When the buyer clicks the browse store button
	Then The buyer is redirected and can see the item catalogue
	And the products table is populated



