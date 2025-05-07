package kp.resource.server.controllers;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

import java.lang.invoke.MethodHandles;

/**
 * An HTML controller that demonstrates Quarkus Keycloak policy enforcement with
 * the "DISABLED" enforcement mode. This endpoint is completely unrestricted and
 * does not require authentication or authorization.
 *
 * <p><b>Warning:</b> Endpoints with enforcement-mode=DISABLED have no access controls
 * and are open to everyone.</p>
 */
@Path("/unrestricted")
public class UnrestrictedResourceController {
    private static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());
    private static final String UNRESTRICTED_ENDPOINT_PAGE = """
            <!DOCTYPE html>
            <html lang="en">
            <head>
                <meta charset="UTF-8">
                <title>Unrestricted Access</title>
                <style>
                    body {
                      background-color: blue
                    }
                    div {
                      border: 20px outset red;
                      background-color: yellow;
                      margin: auto;
                      width: 50%;
                    }
                    p {
                      color: red;
                      text-align: center;
                      font-size: 50px;
                    }
                </style>
            </head>
            <body>
                <div>
                    <p>UNRESTRICTED</p>
                    <p>ACCESS</p>
                </div>
            </body>
            </html>
            """;

    /**
     * Handles GET requests to the "/unrestricted" path.
     *
     * @return An HTML page with a simple message indicating that this is an unrestricted endpoint.
     */
    @GET
    public Response getUnrestrictedResource() {

        logger.info("getUnrestrictedResource():");
        return Response.ok(UNRESTRICTED_ENDPOINT_PAGE, MediaType.TEXT_HTML).build();
    }
}
