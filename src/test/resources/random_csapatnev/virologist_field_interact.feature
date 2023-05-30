Feature: Does Virologist interacts with Field properly?
  Tests if a Virologist's Field interact does the proper actions.

  Scenario: Virologist interacts with Warehouse
    Given Virologist is on a Warehouse
    When I ask if he gained materials
    Then He should answer "Yes I gained" materials

  Scenario: Virologist interacts with Safehouse
    Given Virologist is on a Safehouse
    When I ask if he gained a new Gear
    Then He should answer "Yes I gained" a new Gear

#  Scenario: Virologist interacts with Laboratory
#    Given Virologist is on a Laboratory
#    When I ask if he learnt a new Agent
#    Then He should answer "Yes I gained" a new Agent