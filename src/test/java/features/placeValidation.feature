Feature: Validating Place API's
  Scenario Outline: Verify if Place is being Successfully added using AddPlaceAPI
    Given Add Place PayLoad with "<name>" "<language>" "<address>"
    When user calls "addPlaceApi" with "Post" http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"
    And "scope" in responce body is "APP"
    And verify place_Id created maps to "<name>" using "getPlaceApi"

    Examples:
    |name | language | address     |
    |Haus | English  | Dresdner St |
    #|MyNewHous|Spanish|Sea cross centr|




