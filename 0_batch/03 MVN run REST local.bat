@echo off
set JAVA_HOME=C:\PROGRA~1\JAVA\JDK-24
set MODULE=resource-server-rest
set TITLE=Resource Server REST
set JAR=..\%MODULE%\target\quarkus-app\quarkus-run.jar
::set DEBUG_ARGS=-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005

if exist %JAR% call %JAVA_HOME%\bin\java ^
  -Dquarkus.http.port=9091 ^
  -Dquarkus.oidc.auth-server-url=https://localhost:8443/realms/quarkus ^
  %DEBUG_ARGS% -jar %JAR%
pause