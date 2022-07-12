Feature: Product List Feature

Scenario: Buyer is able to see a list of items.
	Given a buyer is on the home page
	And test buyer account is loaded
	When the buyer clicks the browse store button
	Then The buyer is redirected and can see the item catalogue
	And the products table is populated



