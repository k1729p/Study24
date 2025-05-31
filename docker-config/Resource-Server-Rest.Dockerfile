FROM registry.access.redhat.com/ubi9/openjdk-21:latest
# We make four distinct layers so if there are application changes the library layers can be re-used
COPY --chown=185 resource-server-rest/target/quarkus-app/lib/ /deployments/lib/
COPY --chown=185 resource-server-rest/target/quarkus-app/*.jar /deployments/
COPY --chown=185 resource-server-rest/target/quarkus-app/app/ /deployments/app/
COPY --chown=185 resource-server-rest/target/quarkus-app/quarkus/ /deployments/quarkus/
EXPOSE 8081
USER 185
ENV JAVA_OPTS_APPEND="-Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager"
ENV JAVA_APP_JAR="/deployments/quarkus-run.jar"

ENTRYPOINT ["/bin/sh", "-c", " \
  echo 'Waiting for Keycloak to initialize...'; \
  until curl --silent --insecure https://keycloak:8443/realms/quarkus > /dev/null; do \
    echo 'Keycloak not ready, retrying in 10 seconds...'; \
    sleep 10; \
  done; \
  echo 'Keycloak is ready. Starting the application...'; \
  exec /opt/jboss/container/java/run/run-java.sh" \
]