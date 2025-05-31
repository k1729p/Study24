@echo off
set JAVA_HOME=C:\PROGRA~1\JAVA\JDK-24
set M2_HOME=c:\\tools\\apache-maven-3.9.9
set MAVEN_OPTS="--enable-native-access=ALL-UNNAMED"
set SKIP_TESTS=-DskipTests
set MODULE=resource-server-html
set TITLE=Resource Server HTML
set JAR=..\%MODULE%\target\quarkus-app\quarkus-run.jar
::set DEBUG_ARGS=-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005

call %M2_HOME%\bin\mvn -f ..\pom.xml --projects %MODULE% %SKIP_TESTS% clean package quarkus:build
if exist %JAR% start "%TITLE%" /MAX %JAVA_HOME%\bin\java %DEBUG_ARGS% -jar %JAR%
pause