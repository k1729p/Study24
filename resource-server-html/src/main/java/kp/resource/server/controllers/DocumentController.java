package kp.resource.server.controllers;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import kp.resource.server.model.ClassificationLevel;
import org.jboss.logging.Logger;

import java.lang.invoke.MethodHandles;

/**
 * HTML controller for managing document classifications and access levels.
 * This controller provides endpoints for retrieving documents
 * based on their classification levels.
 */
@Path("/api/document")
public class DocumentController {
    private static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());
    /**
     * The official document.
     */
    static final String OFFICIAL_DOCUMENT = "The official information.";
    /**
     * The restricted document.
     */
    static final String RESTRICTED_DOCUMENT = "The restricted information.";
    /**
     * The confidential document.
     */
    static final String CONFIDENTIAL_DOCUMENT = "The confidential information.";
    /**
     * The secret document.
     */
    static final String SECRET_DOCUMENT = "The secret information.";

    /**
     * Retrieves the document classified as "Official".
     *
     * @return An HTML page indicating successful access to the "Official" document.
     */
    @Path("/official")
    @GET
    public Response getOfficialDocument() {

        logger.info("getOfficialDocument():");
        return Response.ok(
                ClassificationLevel.OFFICIAL.createHtmlPageWithDocument(OFFICIAL_DOCUMENT),
                MediaType.TEXT_HTML).build();
    }

    /**
     * Retrieves the document classified as "Restricted".
     *
     * @return An HTML page indicating successful access to the "Restricted" document.
     */
    @Path("/restricted")
    @GET
    public Response getRestrictedDocument() {

        logger.info("getRestrictedDocument():");
        return Response.ok(
                ClassificationLevel.RESTRICTED.createHtmlPageWithDocument(RESTRICTED_DOCUMENT),
                MediaType.TEXT_HTML).build();
    }

    /**
     * Retrieves the document classified as "Confidential".
     *
     * @return An HTML page indicating successful access to the "Confidential" document.
     */
    @Path("/confidential")
    @GET
    public Response getConfidentialDocument() {

        logger.info("getConfidentialDocument():");
        return Response.ok(
                ClassificationLevel.CONFIDENTIAL.createHtmlPageWithDocument(CONFIDENTIAL_DOCUMENT),
                MediaType.TEXT_HTML).build();
    }

    /**
     * Retrieves the document classified as "Secret".
     *
     * @return An HTML page indicating successful access to the "Secret" document.
     */
    @Path("/secret")
    @GET
    public Response getSecretDocument() {

        logger.info("getSecretDocument():");
        return Response.ok(
                ClassificationLevel.SECRET.createHtmlPageWithDocument(SECRET_DOCUMENT),
                MediaType.TEXT_HTML).build();
    }
}
