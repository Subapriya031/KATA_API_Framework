package com.booking.stepdefinitions;

import com.booking.POJO.BookingDates;
import com.booking.POJO.CreateBookingRequest;
import com.booking.utils.APIUtils;
import com.booking.utils.BookingContext;
import com.booking.utils.ConfigManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.*;
import io.restassured.response.Response;

import java.util.*;
import java.util.stream.Collectors;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CreateBooking {
    public Response response;
    public CreateBookingRequest createBookingRequest;
    Map<String, String> queryParams;
    String endpoint;

    @Given("I have booking details {string}, {string}, {string}, {string},{string},{string}, {string}")
    public void iHaveBookingDetails(String firstname, String lastname, String depositpaid, String email, String phone, String checkin, String checkout) {
        CreateBookingRequest request = new CreateBookingRequest();
        request.setFirstname(valueOrEmpty(firstname));
        request.setLastname(valueOrEmpty(lastname));
        request.setDepositpaid(Boolean.parseBoolean(valueOrEmpty(depositpaid)));
        request.setEmail(valueOrEmpty(email));
        request.setPhone(valueOrEmpty(phone));
        final int randomId = new Random().nextInt(101) + 100; // Generate random id
        BookingContext.set("roomId",String.valueOf(randomId));
        request.setRoomId(randomId);
        System.out.println("Random Room ID: " + randomId);

        // Handle nested object for booking dates
        BookingDates dates = new BookingDates();
        dates.setCheckin(checkin);
        dates.setCheckout(checkout);
        request.setBookingdates(dates);

        // Save the object to use in other steps
        this.createBookingRequest = request;
        System.out.println("Booking request: " + request);

    }

    @When("I send create booking request")
    public void i_send_create_booking_request() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(createBookingRequest);
        System.out.println(jsonString);
        response = APIUtils.post(ConfigManager.getProperty("endpoint.createBooking"), createBookingRequest);

        System.out.println(response.asString());

//		System.out.println("Response Status Code: " + response.getStatusCode());
//		System.out.println("Response Body: " + response.asString());

    }


    @Then("the response should match the JSON schema {string} if status code is 200")
    public void the_response_should_match_the_json_schema_if_status_code_is_200(String schemaFileName) {
        if (response.statusCode() == 200) {
            assertThat(response.getBody().asString(), matchesJsonSchemaInClasspath("schemas/" + schemaFileName));
        }
    }

    @Then("the response should contain error message {string}")
    public void the_response_should_contain_error_message(String expectedMessage) {
        List<String> errors = response.jsonPath().getList("errors");
        assertThat("Expected error message not found in errors array",
                errors, hasItem(expectedMessage));

    }
    @When("the user fetches booking summary with invalid room id {string}")
    public void theUserFetchesBookingSummaryWithInvalidRoomId(String InvalidRoomId) {
        endpoint = ConfigManager.getProperty("endpoint.getBooking");
        queryParams = new HashMap<>();
        queryParams.put("roomId", InvalidRoomId);
        response = APIUtils.get(endpoint, queryParams);

    }
    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(Integer expectedStatus) {
        response.then().assertThat().statusCode(expectedStatus);
    }


    @And("verify the Response")
    public void verifyTheResponse() throws Exception {
        // Print response details
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.asString());
        assertEquals("Unexpected status code", 200, response.getStatusCode());
        List<List<CreateBookingRequest>> wrapped = Collections.singletonList(Collections.singletonList(createBookingRequest));
        ObjectMapper mapper = new ObjectMapper();
        String expectedJson = mapper.writeValueAsString(wrapped);
        assertEquals("Response JSON doesn't match expected", expectedJson, response.asString());
    }




    @Then("I receive status code {string}")
    public void iReceiveStatusCode(String statusCodeString) {
        APIUtils.validateStatusCode(response, Integer.parseInt(statusCodeString));
        try {
            Thread.sleep(5000); // 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Then("the response contains error message {string}")
    public void the_response_contains_error_message(String expectedMessagesCsv) {
        List<String> actualErrors = response.jsonPath().getList("errors");
        List<String> expectedErrors = parseExpectedErrors(expectedMessagesCsv);

        expectedErrors.forEach(expected ->
                assertTrue("Expected error message not found: " + expected, containsIgnoreCase(actualErrors, expected))
        );
    }

    private List<String> parseExpectedErrors(String csv) {
        return Arrays.stream(csv.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    private boolean containsIgnoreCase(List<String> list, String value) {
        return list.stream().anyMatch(item -> item.equalsIgnoreCase(value));
    }

    private String valueOrEmpty(String value) {
        return value == null ? "" : value;
    }

}
