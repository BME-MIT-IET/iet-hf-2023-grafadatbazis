Feature: Can Virologist1 use Agent on Virologist2?
  Tests if Virologist1 can use an Agent on Virologist2.
  Scenario: Virologist1 can use Agent on Virologist2
    Given Virologist1 and Virologist2 stands on the same Field
    Given Virologist1 has the Agent crafted
    When I ask if he could use Agent on Virologist2
    Then He should answer "Yes I could" use Agent on Virologist2

  Scenario: Virologist1 doesn't have the Agent crafted
    Given Virologist1 and Virologist2 stands on the same Field
    Given Virologist1 does not have the Agent crafted
    When I ask if he could use Agent on Virologist2
    Then He should answer "No I couldn't" use Agent on Virologist2

  Scenario: Virologist1 and Virologist2 is not on the same Field
    Given Virologist1 and Virologist2 is not on the same Field
    When I ask if he could use Agent on Virologist2
    Then He should answer "No I couldn't" use Agent on Virologist2