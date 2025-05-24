@echo off
rmdir /S /Q d:\tmp\export
mkdir d:\tmp\export
docker cp --quiet realm_export.sh keycloak:/tmp/realm_export.sh
docker exec -t keycloak sh -c "/tmp/realm_export.sh"
docker cp keycloak:/tmp/export/quarkus-realm.json d:/tmp/export/quarkus-realm.json
docker cp keycloak:/tmp/export/quarkus-users-0.json d:/tmp/export/quarkus-users-0.json
pause