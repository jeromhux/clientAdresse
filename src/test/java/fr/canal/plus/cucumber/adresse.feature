Feature: Change the address of a subscriber

Scenario: Modification of the address of a subscriber residing in France without effective date
Given a client with a main address active in France
 When the advisor connected to canal changes the client address without an effective date
 Then the modified client address is recorded on all subscriber contracts
 Then an address change movement is created
