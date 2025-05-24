package kp.resource.server.controllers;

import io.quarkus.test.junit.QuarkusTestProfile;

import java.util.Map;

/**
 * The test profile using host name 'localhost'.
 */
public class CustomTestProfile implements QuarkusTestProfile {
    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, String> getConfigOverrides() {
        return Map.of("quarkus.oidc.auth-server-url", "https://localhost:8443/realms/quarkus");
    }
}