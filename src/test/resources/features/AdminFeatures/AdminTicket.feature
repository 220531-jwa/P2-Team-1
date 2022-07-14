Feature: Admin tickets

Scenario: Admin can look at an individual ticket
	Given Admin account is loaded
	And Admin is on view tickets page
	When Admin selects single ticket
	Then they are taken to that ticket page

Scenario: Admin can alter the status of an individual ticket
	Given Admin account is loaded
	And Admin is on view tickets page
	When Admin selects single ticket
	And Admin changes status
	Then the new status is displayed
	
