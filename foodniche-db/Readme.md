#Quick recreate database scripts

***Disclaimer***
I use a mac system. I have not tested the windows .bat script yet. If you run into problems please let me know.
For unix/linux systems running 'chmod 755 recreateDb.sh' would make it runnable.

The future of the automated update database plugin is automated integration test.

1. Create a dbConfig properties file, in foodniche-server/src/main/resources with your chosen profile name, in the format dbConfig.***PROFILE_NAME***.properties. 
   I created one named dbConfig.*VIV*.properties
2. Run the script for your operating system
3. Enter the PROFILE_NAME chosen earlier 