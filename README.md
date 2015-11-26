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