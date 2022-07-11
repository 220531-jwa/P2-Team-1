Feature: Submit new ticket page works

Scenario: Buyer can create new support ticket
	Given test buyer account is loaded
	And a buyer is on the submit new ticket page
	When the buyer enters fields and presses submit
	Then the ticket in question is displayed

