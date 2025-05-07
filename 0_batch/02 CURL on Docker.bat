@echo off
docker cp --quiet ../docker-config/tests/call_endpoints.sh resource-server-rest:/home/default/call_endpoints.sh
docker exec -t resource-server-rest sh -c "/home/default/call_endpoints.sh"
pause