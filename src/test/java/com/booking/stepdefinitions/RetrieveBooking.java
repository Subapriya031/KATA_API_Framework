package com.booking.stepdefinitions;

import com.booking.utils.APIUtils;
import com.booking.utils.BookingContext;
import com.booking.utils.ConfigManager;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assume.assumeTrue;

public class RetrieveBooking {

    private Response response;
    Map<String, String> queryParams;


    @Then("I receive status code {int}")
    public void i_receive_status_code(Integer expectedStatus) {
        assertEquals((int) expectedStatus, response.getStatusCode());
    }

    @Then("if status code is 200")
    public void if_status_code_is_200() {
        assumeTrue(response.getStatusCode() == 200);
    }

    @Then("the response matches the Booking Details schema based on {string}")
    public void the_response_matches_the_booking_details_schema_based_on(String idType) {
        if (response.getStatusCode() != 200) return;

        Map<String, String> schemaMap = Map.of(
                "bookingid", "schemas/bookingDetailsByBookingId.json",
                "roomid", "schemas/bookingDetailsByRoomId.json"
        );
        String schemaPath = schemaMap.get(idType.toLowerCase());
        assertNotNull("Unsupported idType for schema validation: " + idType, schemaPath);

        response.then().assertThat()
                .body(matchesJsonSchemaInClasspath(schemaPath));
    }


    @When("I have the room id")
    public void iHaveTheRoomId() {
        queryParams = new HashMap<>();
        queryParams.put("roomId", String.valueOf(BookingContext.get("roomId")));

    }

    @When("I send a GET request to retrieve booking details")
    public void iSendAGETRequestToRetrieveBookingDetails() {
        response = APIUtils.get(ConfigManager.getProperty("endpoint.getBooking"), queryParams);
        System.out.println(response);

    }
}
