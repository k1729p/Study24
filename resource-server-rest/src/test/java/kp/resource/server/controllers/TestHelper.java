package kp.resource.server.controllers;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * The helper class for testing authentication and request building.
 * <p>
 * This class provides utilities for obtaining authenticated requests and handling access tokens.
 * </p>
 */
public class TestHelper {
    @SuppressWarnings("SpellCheckingInspection")
    private static final String CLIENT_SECRET = "2VZCrWwcf1nOF7u4tcvlNvoMrIM8hMnh";
    private static final String USER_PASSWORD = "miki";
    private static final String TOKEN_URL =
            "https://localhost:8443/realms/quarkus/protocol/openid-connect/token";
    private static final String CLIENT_ID = "backend-service";
    private static final String CONTENT_TYPE_FORM_URLENCODED = "application/x-www-form-urlencoded";

    /**
     * Constructs an authenticated request for the given username.
     *
     * @param username the username for which the authenticated request is required
     * @return {@link RequestSpecification} with the access token attached
     */
    static RequestSpecification getAuthenticatedRequest(String username) {

        final Response response = requestAccessToken(username);
        final String accessToken = response.jsonPath().getString("access_token");
        return RestAssured.given().auth().oauth2(accessToken);
    }

    /**
     * Requests an access token for the specified username.
     *
     * @param username the username for which the access token is required
     * @return the {@link Response} containing the access token
     * @throws RuntimeException if the access token retrieval fails
     */
    private static Response requestAccessToken(String username) {
        try {
            final Response response = RestAssured.given()
                    .relaxedHTTPSValidation()
                    .contentType(CONTENT_TYPE_FORM_URLENCODED)
                    .formParam("grant_type", "password")
                    .formParam("client_id", CLIENT_ID)
                    .formParam("client_secret", CLIENT_SECRET)
                    .formParam("username", username)
                    .formParam("password", USER_PASSWORD)
                    .post(TOKEN_URL);
            if (response.getStatusCode() != 200) {
                throw new RuntimeException(String.format(
                        "Failed to obtain access token. HTTP Status: %d, Response Body: %s",
                        response.getStatusCode(), response.getBody().asString()
                ));
            }
            return response;
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while retrieving the access token", e);
        }
    }
}