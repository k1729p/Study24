@echo off
set JAVA_HOME=C:\PROGRA~1\JAVA\JDK-24
set M2_HOME=c:\\tools\\apache-maven-3.9.9
set MAVEN_OPTS="--enable-native-access=ALL-UNNAMED"
set MODULE=resource-server-rest

docker start keycloak
call %M2_HOME%\bin\mvn -f ..\pom.xml --projects %MODULE% compile test
pause