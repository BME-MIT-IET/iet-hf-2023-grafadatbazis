Feature: Can Bear destory materials on a Warehouse
  Tests if a Bear is able to destory the materials on a Warehouse
  Scenario: Bear is able to destory the materials on a Warehouse
    Given Bear is on a Warehouse
    When I ask if he could destroy the materials
    Then He should answer "Yes I could" destroy the materials

  Scenario: Bear is not able to destory the materials on a Warehouse
    Given Bear is not on a Warehouse
    When I ask if he could destroy the materials
    Then He should answer "No I couldn't" destroy the materials