Feature: Can the Virologist move in different circumstances?
  Tests whether Virologist can to different fields in the game.
  Scenario: Normal movement
    Given Virologist has no effects affecting their movement
    Given Virologist stands on a Field with one neighbour
    When I ask whether he could move to that field
    Then He should answer "Yes I could" move

  Scenario: Virologist is paralyzed
    Given Virologist is paralyzed
    Given Virologist stands on a Field with one neighbour
    When I ask whether he could move to that field
    Then He should answer "No I couldn't" move