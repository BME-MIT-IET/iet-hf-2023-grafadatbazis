Feature: Can Virologist craft Agent?
  Tests whether Virologist can craft different Agents and Vaccines
  under different circumstances.
# Could be refactored to use some sort of test generation, but it would
# cause it to be less readable and less usable as an example
  Scenario: Virologist cannot craft as they have no resources
    Given Virologist knows Agent
    Given Virologist has 0 AminoAcid and 0 Nucleotid
    When I ask whether he could craft Agent
    Then He should answer "No"
  Scenario:
    Given Virologist knows Agent
    Given Virologist has 100 AminoAcid and 100 Nucleotid
    When I ask whether he could craft Agent
    Then He should answer "Yes"
  Scenario:
    Given Virologist does not know Agent
    Given Virologist has 100 AminoAcid and 100 Nucleotid
    When I ask whether he could craft Agent
    Then He should answer "No"