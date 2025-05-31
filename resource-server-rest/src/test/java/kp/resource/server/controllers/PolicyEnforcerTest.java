package kp.resource.server.controllers;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

import static org.apache.http.HttpStatus.*;

/**
 * Tests for the policy enforcer, verifying access control
 * to document endpoints based on user roles and authentication.
 */
@QuarkusTest
@TestProfile(CustomTestProfile.class)
class PolicyEnforcerTest {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getName());
    private static final String DOCUMENT_ENDPOINT = "/api/document/%s";

    static {
        RestAssured.useRelaxedHTTPSValidation(); // Disable SSL validation for testing purposes
    }

    /**
     * Verifies that authorized users with the correct role can successfully
     * access the specified document endpoint and return an HTTP 200 (OK) response,
     * as well as the correct document content in the response body.
     *
     * @param classificationLevel the classification level of the document
     *                            (e.g., "official", "restricted", etc.)
     * @param username            the name of the user attempting to access the document
     */
    @ParameterizedTest
    @CsvSource({
            "official, alice",
            "official, bob",
            "official, charlie",
            "official, david",
            "restricted, alice",
            "restricted, bob",
            "restricted, charlie",
            "confidential, alice",
            "confidential, bob",
            "secret, alice"
    })
    @DisplayName("🟩 should get document")
    void shouldGetDocument(String classificationLevel, String username) {
        // GIVEN
        final RequestSpecification specification = TestHelper.getAuthenticatedRequest(username);
        // WHEN
        final Response response = specification.when()
                .get(DOCUMENT_ENDPOINT.formatted(classificationLevel));
        // THEN
        response.then().statusCode(SC_OK);
        final String expectedContent = switch (classificationLevel) {
            case "official" -> DocumentController.OFFICIAL_DOCUMENT;
            case "restricted" -> DocumentController.RESTRICTED_DOCUMENT;
            case "confidential" -> DocumentController.CONFIDENTIAL_DOCUMENT;
            case "secret" -> DocumentController.SECRET_DOCUMENT;
            default -> throw new IllegalArgumentException(
                    "Unknown classification level[%s]".formatted(classificationLevel));
        };
        response.then().body(org.hamcrest.Matchers.equalTo(expectedContent));
        logger.info("shouldGetDocument(): classificationLevel[{}], username[{}]",
                classificationLevel, username);
    }

    /**
     * Verifies that authorized users without the correct role cannot
     * access the specified document endpoint and return an HTTP 403 (Forbidden) response.
     *
     * @param classificationLevel the classification level of the document
     *                            (e.g., "restricted", "confidential", etc.)
     * @param username            the name of the user attempting to access the document
     */
    @ParameterizedTest
    @CsvSource({
            "restricted, david",
            "confidential, charlie",
            "confidential, david",
            "secret, bob",
            "secret, charlie",
            "secret, david"
    })
    @DisplayName("🟥 should not get document")
    void shouldNotGetDocument(String classificationLevel, String username) {
        // GIVEN
        final RequestSpecification specification = TestHelper.getAuthenticatedRequest(username);
        // WHEN
        final Response response = specification.when()
                .get(DOCUMENT_ENDPOINT.formatted(classificationLevel));
        // THEN
        response.then().statusCode(SC_FORBIDDEN);
        logger.info("shouldNotGetDocument(): classificationLevel[{}], username[{}]",
                classificationLevel, username);
    }

    /**
     * Verifies that unauthenticated users cannot access any document endpoint
     * and return an HTTP 401 (Unauthorized) response.
     *
     * @param classificationLevel the classification level of the document
     *                            (e.g., "official", "restricted", etc.)
     */
    @ParameterizedTest
    @CsvSource({"official", "restricted", "confidential", "secret"})
    @DisplayName("🟥 should not get document when unauthenticated")
    void shouldNotGetDocumentWhenUnauthenticated(String classificationLevel) {

        // GIVEN
        final RequestSpecification unauthenticatedSpecification = RestAssured.given();
        // WHEN
        final Response response = unauthenticatedSpecification.when()
                .get(DOCUMENT_ENDPOINT.formatted(classificationLevel));
        // THEN
        response.then().statusCode(SC_UNAUTHORIZED);
        logger.info("shouldNotGetDocumentWhenUnauthenticated(): classificationLevel[{}]",
                classificationLevel);
    }
}
