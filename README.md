# FoodNiche Project

We may need to add here some words about what we do and where are we going

# Release and deploy

See details on those major steps below

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

# Implementation details

Some service-specific implementation details goes below

## Environment definition

We have declared 3 environments now:
 
* DEV 
* QA 
* PROD
 
 Environment is set by providing java option in *Tomcat* script.
 
 For unix systems it's *catalina.sh*. U need to add there as 1st line: **export JAVA_OPTS=-Dspring.profiles.active=DEV**  
 
 For windows systems it's *catalina.bat*. U need to add there as 1st line: **set JAVA_OPTS=-Dspring.profiles.active=DEV**
   
   Then it will be resolved by Spring framework like for **dbConfig.*.properties**. 
   
   Check *foodniche-server\src\main\resources\META-INF\application-context.xml* and see how it uses **spring.profiles.active** variable.

## EmailAPI

EmailAPI is a java class responsible for sending emails.

It has specific for event methods (like user registration, friend request etc.) and also generic method for sending emails.

Generic method is here: *com.foodniche.rest.services.email.EmailAPI.sendMail*

Service is using Velocity engine for rendering emails. Velocity templates contained here: *foodniche-server\src\main\resources\email* .

Configuration files for **EmailAPI**:

* foodniche-server\src\main\resources\META-INF\spring-mail.xml
* foodniche-server\src\main\resources\mail.DEV.properties
* foodniche-server\src\main\resources\mail.PROD.properties
* foodniche-server\src\main\resources\mail.QA.properties
* foodniche-server\src\main\resources\javamail.DEV.properties
* foodniche-server\src\main\resources\javamail.PROD.properties
* foodniche-server\src\main\resources\javamail.QA.properties


# Future implementations

### Media files storage

It is important to avoid application (REST) server to stream media files to client.

The only operation REST server should perform - is to **store** media files. And even this operation better to be delegated to separate small-sized app server.

Here is the solution:

* REST server accepts media file and stores it in network drive

* there is simple web server kinda NGINX - it also has access to network drive and can stream media files to client
  
* when client requests images as part of some entity from REST server - REST server generates links to NGINX web server.

* client takes generated media file URLs and downloads files from NGINX server

Another useful options is to drop NGINX + network drive. This can be easily replaced by using S3 storage.