package com.booking.utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TokenManager {
    private static String cachedToken;


//     Returns an auth token (cached after first retrieval).

    public static String getAuthToken() {
        if (cachedToken == null) {
            cachedToken = requestNewToken();
        }
        return cachedToken;
    }
    private static String requestNewToken() {
        String loginUrl = getLoginUrl();
        String requestBody = buildAuthRequestBody();

        Response response = sendAuthRequest(loginUrl, requestBody);
        String token = extractToken(response);

        extractToken(response);

        System.out.println("Session token retrieved successfully.");
        return token;
    }

    private static String getLoginUrl() {
        return ConfigManager.getProperty("loginurl") + "/api/auth/login";
    }

    private static String buildAuthRequestBody() {
        String username = ConfigManager.getProperty("auth.user");
        String password = ConfigManager.getProperty("auth.pass");
        return String.format("{ \"username\": \"%s\", \"password\": \"%s\" }", username, password);
    }

    private static Response sendAuthRequest(String url, String body) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(body)
                .post(url)
                .then()
                .log().ifValidationFails()
                .extract()
                .response();
    }

    private static String extractToken(Response response) {
        return response.jsonPath().getString("token");
    }
}
