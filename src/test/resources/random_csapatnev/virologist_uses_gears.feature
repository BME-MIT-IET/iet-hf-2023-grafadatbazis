Feature: Can Virologist equip Gear?
  Tests if Virologist can equip a Gear.
  Scenario: Virologist can equip Gear
    Given Virologist has the Gear
    Given Virologist has less then 3 Gears equipped
    When I ask if he could equip Gear
    Then He should answer "Yes I could" equip Gear

  Scenario: Virologist does not have Gear
    Given Virologist does not have the Gear
    When I ask if he could equip Gear
    Then He should answer "No I couldn't" equip Gear

  Scenario: Virologist already has 3 Gears equipped
    Given Virologist has the Gear
    Given Virologist has more then 3 Gears equipped
    When I ask if he could equip Gear
    Then He should answer "No I couldn't" equip Gear
 