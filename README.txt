Instructor: Medhat Saleh
Final Project : Description and Deliverables 
Create a small application with JAVA/MySQL with the airline database (use the given database from the folder (ariline.sql)) Notes: 
	1.	This project must be done individually. No copying is allowed.  
	2.	You need to make sure that your application is runnable on OMEGA/MYSQL command  line.  
	3.	To get full credit, your submitted code should be runnable on Omega terminal and do 3  given tasks correctly. Also, your code with error does have any credit.  
	4.	**Platform: We ONLY use MySQL RDBMS on Omega@UTA.EDU for testing your  queries on Airline Database (airline1.sql).  
Instruction to create your Application 
Write a Program with the Java Programming language and the standard JDBC API to create the following Views: 
V1. Flights with no assigned Pilots 
UnassignedPilotFlights(Flight_Number, Seq, Flight_Date) 
V2. Planes Due for Maintenance: for planes that were last maintained over 60 days ago. 
DueForMaintenacePlanes(PlaneID, Maker, Model, LastMaintained) 
V3. Pilot flying assignments: 
PilotFlyAssignments(PilotID, Pilot_Name, Flight_Number, From_City, To_City, Flight_Date) 
V4. Pilot flight legs count for month/year (example 2017-07) 
PilotFlightLegsCount(PilotId, Pilot_Name, Month_year, Flight_Legs_Count) 
Present the user with a simple menu of options: 
   	1. View of Unassigned Pilot Flights 
	2.	View of Due For Maintenance Planes 
	3.	View of Pilot Fly Assignments 
	4.	View of Pilot FlightLegs Count 
	5.	 Quit 
The user chooses one of the above menu options and performs the task (or quit the program for q). When the task is completed display the view as output, properly. Then, the menu returns to the main menu. For example, in choice1, you will create a view (a table named as :UnassignedPilotFlights with following attributes <Flight_Number, Seq, Flight_Date> ). Then your query’s result should be displayed as output. 

Language used: Java with JDBC
How to run the program:
1) Copy the axb2860_P4.java file to omega.
2) Use the command export set CLASSPATH=.:~/mysql-connector-java.jar:$CLASSPATH
3) Type javac axb2860_P4.java
4) run java axb2860_P4
5) Then choose any option 1,2,3,4 and it will execute according to the given instructions and print the output in a table form
6) Screenshots of the working program is attached in working folder for reference.

 
