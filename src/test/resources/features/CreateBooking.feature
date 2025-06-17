Feature: Create Booking API

  @happyPath
  Scenario Outline: Create booking with Valid data
    Given I have booking details "<firstname>", "<lastname>", "<depositpaid>", "<email>","<phone>","<checkin>", "<checkout>"
    When I send create booking request
    Then I receive status code "<statuscode>"
#    Then verify the Response

    Examples:
      | firstname | lastname | depositpaid | email                | phone       | checkin    | checkout   | statuscode |
      | Shaks     | victor   | true        | shak_vicky@gmail.com | 90897967564 | 2025-06-20 | 2025-06-21 | 200        |
      | valess    | jillwews | true        | vales_jill@gmail.com | 98416789109 | 2025-06-25 | 2025-06-26 | 200        |
