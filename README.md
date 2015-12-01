# FoodNiche

## Releasing version

Go to [Jenkins release build plan](http://ci.foodniche.me/job/FoodNiche-release/m2release/)

Fill values in:
 
* *Release Version*: desired release version. use [Semantic Versioning](http://semver.org/) 

* *Development version*: next development version. Always ends with SNAPSHOT suffix

* *Username* / *Password*: project GitHub credentials

Then press **Schedule Maven Release** Build button.

In case of SUCCESS result u will get released versions of artifacts in Jenkins local Maven repo (SSH to CI box):
**/var/lib/jenkins/.m2/repository**

Specific artifact locations can be taken from build log. Check for lines like:

*[INFO] [INFO] Installing /var/lib/jenkins/workspace/Release 1/target/checkout/foodniche-swagger-ui/target/foodniche-swagger-ui-1.0.4.war to /var/lib/jenkins/.m2/repository/com/foodniche/foodniche-swagger-ui/1.0.4/foodniche-swagger-ui-1.0.4.war*

## Deploying version to QA

Currently we support deployment to Tomcat7 on QA box.

[Deploy to Tomcat7 QA build plan](http://ci.foodniche.me/job/Deploy-to-Tomcat-CI/)

U only need to specify desired build version.

Deployment process (automated):

* Tomcat7 will be stopped

* old WARs and exploded WAR folders will be removed (ui and server) from **/var/lib/tomcat7/webapps/** folder
 
* 2 resources will be copied to **/var/lib/tomcat7/webapps/** folder (foodniche-ui-{version}.war -> ROOT.war; foodniche-server-{version}.war -> FoodNicheRest.war)

* Tomcat7 will be started

Swagger UI will be available by this link: [QA Swagger UI](http://api-qa.foodniche.me/swagger)

## Future implementations

### Media files storage

It is important to avoid application (REST) server to stream media files to client.

The only operation REST server should perform - is to **store** media files. And even this operation better to be delegated to separate small-sized app server.

Here is the solution:

* REST server accepts media file and stores it in network drive

* there is simple web server kinda NGINX - it also has access to network drive and can stream media files to client
  
* when client requests images as part of some entity from REST server - REST server generates links to NGINX web server.

* client takes generated media file URLs and downloads files from NGINX server

Another useful options is to drop NGINX + network drive. This can be easily replaced by using S3 storage.