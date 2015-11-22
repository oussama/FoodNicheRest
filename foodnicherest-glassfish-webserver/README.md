#This module is to quickly spin up a glassfish server for development.

1. Run a maven build. it would bundle up a war and place it in the artifacts folder
2. Change the configuration in foodnicherest-core/main/resources/META-INF/glassfish-resources.xml to match your postgress DB settings
3. Run FoodNicheRestDevRunner

#Lessons learnt from creating an embedded glassfish server
It is possible that configuring the Postgresql Driver in glassfish 4.1.1 would be problematic from the UI. There is a reported bug. 
This can be fixed by adding postgresql-9.3-1100.jdbc41.jar to GLASSFISH_HOME/domains/THE_DOMAIN/lib and running a command from command line
*GLASSFISH_HOME/bin/asadmin --host localhost create-jdbc-connection-pool --datasourceclassname org.postgresql.ds.PGConnectionPoolDataSource --restype javax.sql.ConnectionPoolDataSource postgress-connection-pool*

It is a lot easier to configure the glassfish server with the jdbc-connection-pool. This again is much easier to do from command line
*GLASSFISH_HOME/bin/asadmin add-resources _FULL-PATH-TO_/glassfish-resources.xml*

Glassfish 4.1.1 does not come with all the dependencies it needs to read all the annotation. That is another kettle of fish. 

#Merging your code with this one
It may be easier to stash your changes and unstash. I have not changed anything real in the code. However it is important that the PersistenceContext is the same in all classes and in the persistence.xml
Classes -> @PersistenceContext(unitName = "*FoodNicheRest*") and persistence.xml -> persistence-unit  name="*FoodNicheRest*" (Just highlighting the new value so you can do a global search and replace if you want the name changed)
 