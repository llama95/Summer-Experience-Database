Instructions
				
Use MAMP to start the Apache and MySQL servers that will support the database
Make sure the port is set to match that of the code in the “createDatabase.java” file (8889). 
Import Iteration 3 into eclipse. Make sure the “states.txt.” and “Summer expereince survey 2016 for oliver.csv” files are in the project folder
Left click project folder and select Build Path, then Configure Build Path.
Remove the .jar sql file that is marked as an error. 
Right click the java project > then select  “add external archives.”
Add your “mysql-connector-java-5.1.44-bin.jar.” file to ensure a connection to the database.
Run Project (DbQuery contains main method).
 

Database Connection/Creation Side Note 
The code delivered to you should have a properly created database that doesn't allow table duplication due to our usage of a singleton method. If you would like to check the functionality of the createDatabase class/creation of the database you will need to first drop the database that we have already created. This can be done by selecting the “experiences” database > selecting the “operations” tab > and then selecting  “drop the database (DROP).” The program can still be run normally but will now create a new database instead of simply checking to see if the database has already been properly created. 



