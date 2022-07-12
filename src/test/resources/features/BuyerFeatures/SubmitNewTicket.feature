Feature: Submit new ticket page works

Scenario: Buyer can create new support ticket
	Given test buyer account is loaded
	And a buyer is on the submit new ticket page
	When the buyer enters fields and presses submit
	Then buyer is redirected to view ticket
	And the ticket in question is displayed

Scenario: Buyer does not fill in fields when submitting ticket
	Given test buyer account is loaded
	And  a buyer is on the submit new ticket page
	When the buyer enters nothing and presses submit
	Then an error message should appear
