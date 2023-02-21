Feature: Validating Place API


     @tag1
  Scenario Outline: Verify if Place is being Successfully
    Given Add place Payload with "<name>" "<langauge>" "<address>"
    When User call "AddPlaceAPI" with "POST" method
    Then The API call is Success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_id created maps to "<name>" using "getPlaceAPI"

    Examples: 
      | name    | langauge | address            |
      | AAhouse | English  | World cross center |
      
