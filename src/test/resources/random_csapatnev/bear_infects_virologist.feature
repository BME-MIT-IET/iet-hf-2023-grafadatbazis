Feature: Can a Bear infect a Virologist
  Testis if a Bear is able to infect a Virologist
  Scenario: Bear is able to infect a Virologist
    Given Bear and Virologist are on the same Field
    Given Virologist has no protection
    When I ask if he could infect Virologist
    Then He should answer "Yes I could" infect Virologist 

  Scenario: Bear and Virologist are not on the same Field
    Given Bear and Virologist are not on the same Field
    Given Virologist has no protection
    When I ask if he could infect Virologist
    Then He should answer "No I couldn't" infect Virologist 

  Scenario: Bear can't infect a Virologsit cause Virologist has hasProtection
    Given Bear and Virologist are on the same Field
    Given Virologist has protection
    When I ask if he could infect Virologist
    Then He should answer "No I couldn't" infect Virologist 
