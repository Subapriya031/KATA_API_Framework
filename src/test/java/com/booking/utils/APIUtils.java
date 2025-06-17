package com.booking.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Map;

public class APIUtils {

    public static Response post(String endpoint, Object body) {
        return RestAssured.given()
                .body(body)
                .post(endpoint);
    }
    public static Response get(String endpoint, Map<String, String> queryParams) {
        return RestAssured.given()
                .queryParams(queryParams)
                .get(endpoint);
    }

    public static Response put(String endpoint, Object body) {
        return RestAssured.given()
                .body(body)
                .put(endpoint);
    }

    public static void validateStatusCode(Response response, int expectedStatus) {
        response.then().statusCode(expectedStatus);
    }

    public static String getJsonValue(Response response, String jsonPath) {
        return response.jsonPath().getString(jsonPath);
    }
    public static void logResponse(Response response) {
        response.then().log().all();
    }

}
