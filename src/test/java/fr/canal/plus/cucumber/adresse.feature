Feature: Change the address of a subscriber

Scenario Outline: Modification of the client address by a canal connected advisor
Given a client with a main address active in France
 When the advisor connected to <canal> changes the client address without an effective date
 Then the modified client address is recorded on all subscriber contracts
 Then an address change movement is created

Examples:
| canal |
| "FACE" |
| "EC" |

