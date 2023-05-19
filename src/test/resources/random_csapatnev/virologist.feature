Feature: Can Virologist craft Agent?
  Tests whether Virologist can craft different Agents and Vaccines
  under different circumstances.

  Scenario: Virologist cannot craft as they have no resources
    Given Virologist knows Agent
    Given Virologist has 0 AminoAcid and 0 Nucleotid
    When I ask whether he could craft Agent
    Then He should answer "No"