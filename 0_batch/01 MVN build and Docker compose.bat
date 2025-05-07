@echo off
set JAVA_HOME=C:\PROGRA~1\JAVA\JDK-24
set M2_HOME=c:\\tools\\apache-maven-3.9.9
set MAVEN_OPTS="--enable-native-access=ALL-UNNAMED"
set SKIP_TESTS=-DskipTests
set PROJECT=study24
set MODULE=resource-server-rest
set COMPOSE_FILE=..\docker-config\compose.yaml

call %M2_HOME%\bin\mvn -f ..\pom.xml --projects %MODULE% %SKIP_TESTS% clean package quarkus:build

copy /Y ..\keycloak-config\keycloak-keystore.jks d:\tmp\keycloak-keystore.jks > nul 2>&1
copy /Y ..\keycloak-config\quarkus-realm.json d:\tmp\quarkus-realm.json > nul 2>&1
docker container rm --force %MODULE% > nul 2>&1
docker container rm --force keycloak > nul 2>&1
docker image rm --force %PROJECT%-%MODULE% > nul 2>&1
set KEY=Y
set /P KEY="Remove Keycloak image from Docker? [Y] N "
if /i "%KEY:~0,1%"=="Y" (
  docker image rm --force quay.io/keycloak/keycloak:latest > nul 2>&1
)
echo ------------------------------------------------------------------------------------------
docker compose down
docker compose -f %COMPOSE_FILE% -p %PROJECT% up --detach
echo ------------------------------------------------------------------------------------------
docker compose -f %COMPOSE_FILE% -p %PROJECT% ps
echo ------------------------------------------------------------------------------------------
docker compose -f %COMPOSE_FILE% -p %PROJECT% images
:: docker network inspect %PROJECT%_net
pause
