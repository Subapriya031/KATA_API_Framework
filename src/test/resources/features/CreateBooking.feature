Feature: Create Booking API

  @happyPath @skipToken
  Scenario Outline: Create booking with Valid data
    Given I have booking details "<firstname>", "<lastname>", "<depositpaid>", "<email>","<phone>","<checkin>", "<checkout>"
    When I send create booking request
    Then I receive status code "<statuscode>"
#    Then verify the Response

    Examples:
      | firstname | lastname | depositpaid | email                | phone       | checkin    | checkout   | statuscode |
      | Shaks     | victor   | true        | shak_vicky@gmail.com | 90897967564 | 2025-06-20 | 2025-06-21 | 200        |
      | valess    | jillwews | true        | vales_jill@gmail.com | 98416789109 | 2025-06-25 | 2025-06-26 | 200        |

  @Invalid
  Scenario Outline: Create booking with different input combinations and validations
    Given I have booking details "<firstname>", "<lastname>", "<depositpaid>", "<email>","<phone>","<checkin>", "<checkout>"
    When I send create booking request
    Then I receive status code "<statuscode>"
    And the response contains error message "<errormsg>"

    Examples:
      | firstname | lastname | depositpaid | email                  | phone        | checkin    | checkout   | errormsg                                                             | statuscode |
#Email & Phone Validation
      | romy      | luca     | false       |                        | 78786543221  | 2025-06-18 | 2025-06-19 | must not be empty                                                    | 400        |
      | lala      | luca     | false       |                        |              | 2025-06-18 | 2025-06-19 | must not be empty, size must be between 11 and 21, must not be empty | 400        |
      | rony      | robert   | false       | Jrony_robert@gmail.com | 66765451326  | 2024-06-11 | 2024-06-12 | Invalid booking dates                                                | 400        |
      | rony      | luca     | false       | romy@gmail.com         | 123          | 2025-06-12 | 2025-06-15 | size must be between 11 and 21                                       | 400        |
#FirstName Validation
      | j         | luca     | false       | Jrony_robert@gmail.com | 787865432213 | 2025-06-12 | 2025-06-15 | size must be between 3 and 18                                        | 400        |
      |           | shaks    | true        | Jrony_robert@gmail.com | 908979675645 | 2025-06-15 | 2025-06-17 | size must be between 3 and 18, Firstname should not be blank         | 400        |
#LastName Validation
      | rony      | v        | false       | romy&gmail.com         | 787865432213 | 2025-06-12 | 2025-06-15 | must be a well-formed email address                                  | 400        |
      | Romy      |          | true        | Jrony_robert@gmail.com | 554167891099 | 2025-06-11 | 2025-06-12 | size must be between 3 and 30, Lastname should not be blank          | 400        |

# Checkin - Checkout validation
      | rony      | luca     | false       | jerome@gmail.com       | 787865432213 |            | 2025-06-15 | must not be null                                                     | 400        |
      | rony      | luca     | false       | jerome@gmail.com       | 787865432213 | 2025-06-12 |            | must not be null                                                     | 400        |

