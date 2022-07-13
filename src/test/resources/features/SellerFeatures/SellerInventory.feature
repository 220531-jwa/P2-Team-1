Feature: Seller Inventory

Scenario: Seller Inventory Displays their items
	Given A test seller account is loaded
	And seller is on the seller inventory page
	Then seller inventory is displayed
	
Scenario: Seller can view single inventory item
	Given A test seller account is loaded
	And seller is on the seller inventory page
	When the seller selects item to view
	Then seller can see item information

Scenario: Seller can edit item
	Given A test seller account is loaded
	And seller is on the seller inventory page
	When the seller selects item to view
	And edits the item and submits
	Then the edited value is now visible