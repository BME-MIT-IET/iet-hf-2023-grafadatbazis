Feature: Can Virologist kill Bear
  Tests if a Virologist is able to kill a Bear
#  Scenario: Virologist can kill a Bear
#    Given Virologist and Bear is on the same Field
#    Given Virologist has Axe
#    When I ask if he could kill Bear
#    Then He should answer "Yes I could" kill Bear

# Not testable because MainFrameInstance is null
# Leaving code in VirologistKillsBearStepDefs.java 
# so it can be tested if we implement a mocking framework.

  Scenario: Virologist and Bear are on different fields
    Given Virologist and Bear are on different fields
    Given Virologist has Axe
    When I ask if he could kill Bear
    Then He should answer "No I couldn't" kill Bear

  Scenario: Virologist does not have Axe
    Given Virologist and Bear is on the same Field
    Given Virologist does not have Axe
    When I ask if he could kill Bear
    Then He should answer "No I couldn't" kill Bear
