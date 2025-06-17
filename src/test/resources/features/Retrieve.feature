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

