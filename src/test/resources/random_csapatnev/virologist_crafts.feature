Feature: Can Virologist craft Agent?
  Tests whether Virologist can craft different Agents and Vaccines
  under different circumstances.
# Could be refactored to use some sort of test generation, but it would
# cause it to be less readable and less usable as an example
  Scenario: Virologist cannot craft as they have no resources
    Given Virologist knows Agent
    Given Virologist has 0 AminoAcid and 0 Nucleotid
    When I ask whether he could craft Agent
    Then He should answer "No I couldn't" craft
  Scenario: Virologist can craft Agent
    Given Virologist knows Agent
    Given Virologist has 100 AminoAcid and 100 Nucleotid
    When I ask whether he could craft Agent
    Then He should answer "Yes I could" craft
  Scenario: Virologist cannot craft Agent as he does not know the recipe
    Given Virologist does not know Agent
    Given Virologist has 100 AminoAcid and 100 Nucleotid
    When I ask whether he could craft Agent
    Then He should answer "No I couldn't" craft