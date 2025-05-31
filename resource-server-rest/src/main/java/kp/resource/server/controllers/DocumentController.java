package kp.resource.server.controllers;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

import java.lang.invoke.MethodHandles;

/**
 * REST controller for managing document classifications and access levels.
 * This controller provides REST endpoints for retrieving documents
 * categorized by their classification levels.
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
     * @return A response containing the "Official" document's content.
     */
    @Path("/official")
    @GET
    public Response getOfficialDocument() {

        logger.info("getOfficialDocument():");
        return Response.ok(OFFICIAL_DOCUMENT).build();
    }

    /**
     * Retrieves the document classified as "Restricted".
     *
     * @return A response containing the "Restricted" document's content.
     */
    @Path("/restricted")
    @GET
    public Response getRestrictedDocument() {

        logger.info("getRestrictedDocument():");
        return Response.ok(RESTRICTED_DOCUMENT).build();
    }

    /**
     * Retrieves the document classified as "Confidential".
     *
     * @return A response containing the "Confidential" document's content.
     */
    @Path("/confidential")
    @GET
    public Response getConfidentialDocument() {

        logger.info("getConfidentialDocument():");
        return Response.ok(CONFIDENTIAL_DOCUMENT).build();
    }

    /**
     * Retrieves the document classified as "Secret".
     *
     * @return A response containing the "Secret" document's content.
     */
    @Path("/secret")
    @GET
    public Response getSecretDocument() {

        logger.info("getSecretDocument():");
        return Response.ok(SECRET_DOCUMENT).build();
    }
}
