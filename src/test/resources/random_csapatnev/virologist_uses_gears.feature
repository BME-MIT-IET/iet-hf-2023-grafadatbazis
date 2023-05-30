Feature: Can Virologist equip Gear?
  Tests if Virologist can equip a Gear.
  Scenario: Virologist can equip Cloak
    Given Virologist has a Cloak
    Given Virologist has less than 3 Gears equipped
    When I ask if he could equip Cloak
    Then He should answer "Yes I could" equip "CLOAK"

  Scenario: Virologist can equip Gloves
    Given Virologist has Gloves
    Given Virologist has less than 3 Gears equipped
    When I ask if he could equip Gloves
    Then He should answer "Yes I could" equip "GLOVES"

  Scenario: Viroogist can equip Axe
    Given Virologist has an Axe
    Given Virologist has less than 3 Gears equipped
    When I ask if he could equip Axe
    Then He should answer "Yes I could" equip "AXE"

  Scenario: Viroogist can equip Sack
    Given Virologist has a Sack
    Given Virologist has less than 3 Gears equipped
    When I ask if he could equip Sack
    Then He should answer "Yes I could" equip "SACK"

  Scenario: Virologist does not have Gear
    Given Virologist does not have Gloves
    When I ask if he could equip Gloves
    Then He should answer "No I couldn't" equip "GLOVES"

  Scenario: Virologist already has 3 Gears equipped
    Given Virologist has Gloves
    Given Virologist has more than 3 Gears equipped
    When I ask if he could equip Gloves
    Then He should answer "No I couldn't" equip "GLOVES"
 