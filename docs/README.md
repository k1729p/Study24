<!DOCTYPE html>
<html lang="en">
<meta charset="UTF-8">
<body>
<a href="https://github.com/k1729p/Study24/tree/main/docs">
    <img alt="" src="images/ColorScheme.png" height="25" width="800"/>
</a>
<h2 id="contents">Study24 README Contents</h2>

<h3 id="top">Research <a href="https://www.keycloak.org/getting-started/getting-started-docker">Keycloak</a>
    and <a href="https://quarkus.io/">Quarkus</a></h3>

<img src="images/MermaidFlowchart.png" height="630" width="860" alt=""/>

<p>
    Authorization decisions are delegated to Keycloak, an OAuth 2.0-compliant authorization server.
</p>
<p>
    This project consists of two Quarkus applications,
    each designed to serve a distinct purpose within the architecture:
</p>
<ol>
    <li><i>resource-server-rest</i> - serves as an OAuth2 Resource Server,
        handling secure RESTful API requests and ensuring proper authorization.</li>
    <li><i>resource-server-html</i> - operates as an OAuth2 Resource Server,
        focused on securely delivering HTML content and managing browser-based interactions.</li>
</ol>
<p>
    The project leverages the OAuth2 protocol to provide secure access control,
    ensuring that only authorized clients can interact with the resources.
    The resource access permissions are configured directly in Keycloak.
    Quarkus applications use bearer token authorization.
    Refer to the <a href="https://quarkus.io/guides/security-oidc-bearer-token-authentication#overview-of-the-bearer-token-authentication-mechanism-in-quarkus">
    'Bearer token authentication mechanism'</a> diagram.
</p>
<p>
    The Keycloak Authorization extension ('quarkus-keycloak-authorization' Maven dependency)
    extends the OpenID Connect extension ('quarkus-oidc' Maven dependency).
</p>

<p>
    Sections of this project:
</p>
<ol>
    <li><a href="#ONE"><b>Docker Build</b></a></li>
    <li><a href="#TWO"><b>Curl Client on Docker</b></a></li>
    <li><a href="#THREE"><b>Local Curl Client</b></a></li>
    <li><a href="#FOUR"><b>Local Build</b></a></li>
    <li><a href="#FIVE"><b>Web Browser Client</b></a></li>
</ol>

<p>
    Java source code packages:<br>
    <img alt="" src="images/aquaHR-500.png"><br>
    <img alt="" src="images/aquaSquare.png">
    <i>project 'Study24-resource-server-rest', application sources</i>&nbsp;:&nbsp;
    <a href="https://github.com/k1729p/Study24/tree/main/resource-server-rest/src/main/java/kp">kp</a><br>
    <img alt="" src="images/aquaSquare.png">
    <i>project 'Study24-resource-server-rest', test sources</i>&nbsp;:&nbsp;
    <a href="https://github.com/k1729p/Study24/tree/main/resource-server-rest/src/test/java/kp">kp</a><br>
    <img alt="" src="images/aquaSquare.png">
    <i>project 'Study24-resource-server-html', application sources</i>&nbsp;:&nbsp;
    <a href="https://github.com/k1729p/Study24/tree/main/resource-server-html/src/main/java/kp">kp</a><br>
    <img alt="" src="images/aquaHR-500.png">
</p>

<p>
    <img alt="" src="images/yellowHR-500.png"><br>
    <img alt="" src="images/yellowSquare.png">
    <i>project 'Study24-resource-server-rest'</i>&nbsp;:&nbsp;
    <a href="https://htmlpreview.github.io/?https://github.com/k1729p/Study24/blob/main/resource-server-rest/docs/apidocs/overview-tree.html">
        Java API Documentation</a>&nbsp;●&nbsp;
    <a href="https://htmlpreview.github.io/?https://raw.githubusercontent.com/k1729p/Study24/main/resource-server-rest/docs/testapidocs/kp/resource/server/controllers/package-summary.html">
        Java Test API Documentation</a><br>
    <img alt="" src="images/yellowSquare.png">
    <i>project 'Study24-resource-server-html'</i>&nbsp;:&nbsp;
    <a href="https://htmlpreview.github.io/?https://github.com/k1729p/Study24/blob/main/resource-server-html/docs/apidocs/overview-tree.html">
        Java API Documentation</a><br>
    <img alt="" src="images/yellowHR-500.png">
</p>

<hr>
<h3 id="ONE">❶ Docker Build</h3>

<p>Action:<br>
    <img  alt="" src="images/orangeHR-500.png"><br>
    <img  alt="" src="images/orangeSquare.png"> Use the batch file
    <a href="https://github.com/k1729p/Study24/blob/main/0_batch/01%20MVN%20build%20and%20Docker%20compose.bat">
        <i>"01 MVN build and Docker compose.bat"</i></a> to build the images and<br>
    <img  alt="" src="images/orangeSquare.png"><img  alt="" src="images/spacer-32.png">start the containers.<br>
    <img  alt="" src="images/orangeHR-500.png">
</p>

<p><img  alt="" src="images/greenCircle.png">
    1.1. Docker images are built using the following files:
</p>
<ul>
    <li><a href="https://raw.githubusercontent.com/k1729p/Study24/main/docker-config/Resource-Server-Rest.Dockerfile">
        <b>Resource-Server-Rest.Dockerfile</b></a></li>
    <li><a href="https://raw.githubusercontent.com/k1729p/Study24/main/docker-config/compose.yaml">
        <b>compose.yaml</b></a></li>
</ul>
<p><img  alt="" src="images/greenCircle.png">
    1.2. The <a href="images/ScreenshotDockerContainers.png">screenshot</a>
    shows the created Docker containers.
</p>
<p><img  alt="" src="images/greenCircle.png">
    1.3. The resource servers manage documents classified under four classification levels.
    Four users are assigned specific roles corresponding to their access levels.
</p>
<p>
    <img src="images/UsersAndClassificationLevels.png" height="225" width="455" alt=""/>
</p>

<p><img  alt="" src="images/greenCircle.png">
    1.4. Keycloak
    <a href="https://raw.githubusercontent.com/k1729p/Study24/refs/heads/main/0_batch/helpers/realm_export/keycloak_settings_for_quarcus_realm.txt">
        realm 'quarkus'</a> configuration.
</p>

<a href="#top">Back to the top of the page</a>
<hr>
<h3 id="TWO">❷ Curl Client on Docker</h3>

<p>Action:<br>
    <img  alt="" src="images/orangeHR-500.png"><br>
    <img  alt="" src="images/orangeSquare.png"> Use the batch file
    <a href="https://github.com/k1729p/Study24/blob/main/0_batch/02%20CURL%20on%20Docker.bat">
        <i>"02 CURL on Docker.bat"</i></a> to execute the shell script
    <a href="https://raw.githubusercontent.com/k1729p/Study24/refs/heads/main/docker-config/tests/call_endpoints.sh">
        call_endpoints.sh</a>.<br>
    <img  alt="" src="images/orangeHR-500.png">
</p>

<p><img alt="" src="images/greenCircle.png">
    2.1.1. The <b>Official Document</b> endpoint: 'http://localhost:8081/api/document/official/rest'.<br>
    The controller method:
    <a href="https://github.com/k1729p/Study24/blob/main/resource-server-rest/src/main/java/kp/resource/server/controllers/DocumentController.java#L42">
        kp.resource.server.controllers.DocumentController::getOfficialDocument</a>.
</p>

<p><img alt="" src="images/greenCircle.png">
    2.1.2. The <b>Restricted Document</b> endpoint: 'http://localhost:8081/api/document/restricted/rest'.<br>
    The controller method:
    <a href="https://github.com/k1729p/Study24/blob/main/resource-server-rest/src/main/java/kp/resource/server/controllers/DocumentController.java#L55">
        kp.resource.server.controllers.DocumentController::getRestrictedDocument</a>.
</p>

<p><img alt="" src="images/greenCircle.png">
    2.1.3. The <b>Confidential Document</b> endpoint: 'http://localhost:8081/api/document/confidential/rest'.<br>
    The controller method:
    <a href="https://github.com/k1729p/Study24/blob/main/resource-server-rest/src/main/java/kp/resource/server/controllers/DocumentController.java#L68">
        kp.resource.server.controllers.DocumentController::getConfidentialDocument</a>.
</p>

<p><img alt="" src="images/greenCircle.png">
    2.1.4. The <b>Secret Document</b> endpoint: 'http://localhost:8081/api/document/secret/rest'.<br>
    The controller method:
    <a href="https://github.com/k1729p/Study24/blob/main/resource-server-rest/src/main/java/kp/resource/server/controllers/DocumentController.java#L81">
        kp.resource.server.controllers.DocumentController::getSecretDocument</a>.
</p>
<p><img alt="" src="images/greenCircle.png">
    2.2. The results of the shell script 'call_endpoints.sh' execution.
</p>
<p>
    <img src="images/ScreenshotCurlDocker.png" height="885" width="545" alt=""/><br>
    <img alt="" src="images/blackArrowUp.png">
    <i>Screenshot of the console log from the batch file "02 CURL on Docker.bat".</i>
</p>

<a href="#top">Back to the top of the page</a>
<hr>

<h3 id="THREE">❸ Local Curl Client</h3>
<p>Action:<br>
    <img  alt="" src="images/orangeHR-500.png"><br>
    <img  alt="" src="images/orangeSquare.png"> 1. Use the batch file
    <a href="https://github.com/k1729p/Study24/blob/main/0_batch/03%20MVN%20run%20REST%20local.bat">
        <i>"03 MVN run REST local.bat"</i></a>.<br>
    <img  alt="" src="images/orangeSquare.png">
    &nbsp; to start the application 'resource server rest' locally.<br>
    <img  alt="" src="images/orangeSquare.png"> 2. Start the Windows batch script
    <a href="https://github.com/k1729p/Study24/blob/main/0_batch/04%20CURL%20on%20local.bat">
        <i>"04 CURL on local.bat"</i></a>.<br>
    <img  alt="" src="images/orangeHR-500.png">
</p>

<p><img alt="" src="images/greenCircle.png">
    3.1.1. The <b>Official Document</b> endpoint: 'http://localhost:9091/api/document/official/rest'.
</p>

<p><img alt="" src="images/greenCircle.png">
    3.1.2. The <b>Restricted Document</b> endpoint: 'http://localhost:9091/api/document/restricted/rest'.
</p>

<p><img alt="" src="images/greenCircle.png">
    3.1.3. The <b>Confidential Document</b> endpoint: 'http://localhost:9091/api/document/confidential/rest'.
</p>

<p><img alt="" src="images/greenCircle.png">
    3.1.4. The <b>Secret Document</b> endpoint: 'http://localhost:9091/api/document/secret/rest'.
</p>

<p><img  alt="" src="images/greenCircle.png">
    3.2. The <a href="images/ScreenshotCurlLocal.png">screenshot</a>
    shows the console log from the batch file "04 CURL on local.bat".<br>
</p>
<h3 id="FOUR">❹ Local Build</h3>
<p>Action:<br>
    <img  alt="" src="images/orangeHR-500.png"><br>
    <img  alt="" src="images/orangeSquare.png"> Use the batch file
    <a href="https://github.com/k1729p/Study24/blob/main/0_batch/05%20MVN%20build%20and%20run%20HTML%20local.bat">
        <i>"05 MVN build and run HTML local.bat"</i></a> to build and<br>
    <img  alt="" src="images/orangeSquare.png">
    &nbsp; start the application 'resource server html' locally.<br>
    <img  alt="" src="images/orangeHR-500.png">
</p>

<a href="#top">Back to the top of the page</a>
<hr>
<h3 id="FIVE">❺ Web Browser Client</h3>
<p>Action:<br>
    <img  alt="" src="images/orangeHR-500.png"><br>
    <img  alt="" src="images/orangeSquare.png"> Open the file
    <a href="https://github.com/k1729p/Study24/blob/main/0_batch/Links.html">Links.html</a> in a web browser.<br>
    <img  alt="" src="images/orangeHR-500.png">
</p>

<p><img  alt="" src="images/greenCircle.png">
    5.1. The GitHub preview in a browser of the page
    <a href="https://htmlpreview.github.io/?https://github.com/k1729p/Study24/blob/main/0_batch/Links.html">
        Links</a>.
</p>
<p><img  alt="" src="images/greenCircle.png">
    5.2. The <a href="images/ScreenshotKeycloakLoginAuthentication.png">screenshot</a>
    shows Keycloak's login authentication page.
</p>

<p>
    These document endpoints use Keycloak's authorization configurations, requiring the roles
    defined in the Keycloak realm 'quarkus' to enforce role-based access control.
</p>

<p><img alt="" src="images/greenCircle.png">
    5.3.1. The <b>Official Document</b> endpoint: 'http://localhost:9092/api/document/official/html'.
</p>
<p>
    The controller method:
    <a href="https://github.com/k1729p/Study24/blob/main/resource-server-html/src/main/java/kp/resource/server/controllers/DocumentController.java#L44">
        kp.resource.server.controllers.DocumentController::getOfficialDocument</a>.<br>
</p>
<p>
    The <a href="images/ScreenshotOfficialDocument.png">screenshot</a>
    shows the result from the endpoint 'http://localhost:9092/api/document/official/html'.
</p>

<p><img alt="" src="images/greenCircle.png">
    5.3.2. The <b>Restricted Document</b> endpoint: 'http://localhost:9092/api/document/restricted/html'.
</p>
<p>
    The controller method:
    <a href="https://github.com/k1729p/Study24/blob/main/resource-server-html/src/main/java/kp/resource/server/controllers/DocumentController.java#L59">
        kp.resource.server.controllers.DocumentController::getRestrictedDocument</a>.<br>
</p>
<p>
    The <a href="images/ScreenshotRestrictedDocument.png">screenshot</a>
    shows the result from the endpoint 'http://localhost:9092/api/document/restricted/html'.<br>
</p>

<p><img alt="" src="images/greenCircle.png">
    5.3.3. The <b>Confidential Document</b> endpoint: 'http://localhost:9092/api/document/confidential/html'.
</p>
<p>
    The controller method:
    <a href="https://github.com/k1729p/Study24/blob/main/resource-server-html/src/main/java/kp/resource/server/controllers/DocumentController.java#L74">
        kp.resource.server.controllers.DocumentController::getConfidentialDocument</a>.<br>
</p>
<p>
    The <a href="images/ScreenshotConfidentialDocument.png">screenshot</a>
    shows the result from the endpoint 'http://localhost:9092/api/document/confidential/html'.<br>
</p>

<p><img alt="" src="images/greenCircle.png">
    5.3.4. The <b>Secret Document</b> endpoint: 'http://localhost:9092/api/document/secret/html'.
</p>
<p>
    The controller method:
    <a href="https://github.com/k1729p/Study24/blob/main/resource-server-html/src/main/java/kp/resource/server/controllers/DocumentController.java#L89">
        kp.resource.server.controllers.DocumentController::getSecretDocument</a>.<br>
</p>
<p>
    The <a href="images/ScreenshotSecretDocument.png">screenshot</a>
    shows the result from the endpoint 'http://localhost:9092/api/document/secret/html'.<br>
</p>

<p><img alt="" src="images/greenCircle.png">
    5.4. The <b>Resource Server Information</b> endpoint: 'http://localhost:9092/api/info'.
</p>
<p>
    The controller method:
    <a href="https://github.com/k1729p/Study24/blob/main/resource-server-html/src/main/java/kp/resource/server/controllers/ResourceServerInfoController.java#L62">
        kp.resource.server.controllers.ResourceServerInfoController::info</a>.<br>
</p>
<p>
    The <a href="images/ScreenshotResourceServerInformation.png">screenshot</a>
    shows the result from the endpoint 'http://localhost:9092/api/info'.<br>
</p>

<p><img alt="" src="images/greenCircle.png">
    5.5. The <b>Unrestricted</b> endpoint: 'http://localhost:9092/unrestricted'.
</p>
<p>
    The controller method:
    <a href="https://github.com/k1729p/Study24/blob/main/resource-server-html/src/main/java/kp/resource/server/controllers/UnrestrictedResourceController.java#L60">
        kp.resource.server.controllers.UnrestrictedResourceController::getUnrestrictedResource</a>.<br>
</p>
<p>
    The <a href="images/ScreenshotUnrestrictedAccess.png">screenshot</a>
    shows the result from the endpoint 'http://localhost:9092/unrestricted'.<br>
</p>

<a href="#top">Back to the top of the page</a>
<hr>
</body>
</html>