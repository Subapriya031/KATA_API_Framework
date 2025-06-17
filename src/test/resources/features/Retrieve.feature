Feature: Retrieve Booking Details

  @happyPath
  Scenario Outline: Retrieve booking details by valid Room ID
    Given I have booking details "<firstname>", "<lastname>", "<depositpaid>", "<email>","<phone>","<checkin>", "<checkout>"
    And I have the room id
    When I send a GET request to retrieve booking details
    Then I receive status code <statuscode>
    And verify the Response

    Examples:
      | firstname | lastname | depositpaid | email                | phone       | checkin    | checkout   | statuscode |
      | Shaks     | victor   | true        | shak_vicky@gmail.com | 90897967564 | 2025-06-20 | 2025-06-21 | 200        |


  @retrieveBookingWithInvalidRoomId @happyPath
  Scenario Outline: Fetch booking summary with invalid room id input
    Given the user fetches booking summary with invalid room id "<invalidRoomId>"
    Then I receive status code "<statuscode>"
    And the response should contain empty booking summary

    Examples:
      | invalidRoomId |statuscode|
      | 000           |200       |
      | 999           |200       |
      | -1            |200       |

  @retrieveBookingWithInvalidBookingId @errorValidation
  Scenario Outline: Fetch booking summary with invalid booking id input
    Given the user fetches booking summary with invalid booking id "<invalidBookingId>"
    Then the response status code should be <statusCode>

    Examples:
      | invalidBookingId | statusCode |
      | 000              | 404        |
      | -1               | 400        |
      | abc123           | 400        |

