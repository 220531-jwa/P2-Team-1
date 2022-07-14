Feature: Add balance feature

Scenario: User adds balance to their account
	Given a buyer is on the home page
	And test buyer account is loaded
	When the buyer enters number and adds balance
	Then balance added message appears
	
	