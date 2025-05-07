package kp.resource.server.controllers;

import io.quarkus.security.identity.SecurityIdentity;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

import java.lang.invoke.MethodHandles;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Controller for providing resource server information.
 */
@Path("/api/info")
public class ResourceServerInfoController {
    private static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());
    private final SecurityIdentity securityIdentity;
    private static final Function<SecurityIdentity, String> ROLES_FUN = identity -> identity
            .getRoles().stream().map("%n\t%s,"::formatted).sorted().collect(Collectors.joining());
    private static final String INFO_PAGE_FMT = """
            <!DOCTYPE html>
            <html lang="en">
            <head>
                <meta charset="UTF-8">
                <title>Resource Server Information</title>
                <style>
                    body {
                      background-color: wheat
                    }
                </style>
            </head>
            <body>
            <h1>Resource Server Information</h1>
            <hr>
            <pre>%s<pre>
            <hr>
            </body>
            </html>
            """;

    /**
     * Parameterized constructor.
     *
     * @param securityIdentity the security identity
     */
    @Inject
    public ResourceServerInfoController(SecurityIdentity securityIdentity) {
        this.securityIdentity = Objects.requireNonNull(securityIdentity, "SecurityIdentity must not be null");
    }

    /**
     * Displays information derived from the {@link SecurityIdentity} object.
     *
     * @return An HTTP response containing the resource server's information in HTML format.
     */
    @GET
    public Response info() {

        logger.info("info():");
        return Response.ok(
                INFO_PAGE_FMT.formatted("""
                        principal name[%s],
                        roles %s""".formatted(
                        securityIdentity.getPrincipal().getName(),
                        ROLES_FUN.apply(securityIdentity))),
                MediaType.TEXT_HTML).build();
    }
}
