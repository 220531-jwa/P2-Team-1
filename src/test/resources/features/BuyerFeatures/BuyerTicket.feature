Feature: Buyer ticket features

Scenario: Buyer can create new support ticket
	Given a buyer is on the submit new ticket page
	And test buyer account is loaded
	When the buyer enters fields and presses submit
	Then buyer is redirected to view ticket
	And the ticket in question is displayed

Scenario: Buyer does not fill in fields when submitting ticket
	Given a buyer is on the submit new ticket page
	And test buyer account is loaded
	When the buyer enters nothing and presses submit
	Then an error message should appear
	
Scenario: Buyer is able to view all tickets
	Given a buyer is on the home page
	And test buyer account is loaded
	When the buyer clicks view all tickets
	Then they are redirected and can see their tickets
