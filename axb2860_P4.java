// Class: CSE 3330
// Semester: Fall 2018
// Student Name: Babu, Ashwin , axb2860
// Student ID: 1001392860
// Assignment project #4

import java.sql.*;
import java.util.Scanner;
import java.lang.String;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.text.*;

public class axb2860_P4{

   // Database connection
    final static String user = "axb2860";
    final static String password = "Apple123"; //mysql password
    final static String db = "axb2860";
    final static String jdbc = "jdbc:mysql://localhost:3306/"+db+"?user="+user+"&password="+password;

    public static void main ( String[] args ) throws Exception {
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        
        Connection con = DriverManager.getConnection(jdbc);
       
        char ch ;
        Scanner scanner = new Scanner(System.in); //for taking user input
       
        
        do{
            System.out.println("\n\nMenu:\n");
            System.out.println("1. Check if Pilot is busy on a certain day and show the pilot assignments for this day\n");
            System.out.println("2. Assign a pilot to a leg instance\n");
            System.out.println("3. Add a Pilot\n");
            System.out.println("4. Quit\n");
            
           
            System.out.println("Select from the above menu\n");
            ch = scanner.next().charAt(0);
            
            switch(ch) {

                // Case 1 for searching the pilot in the database
                case '1': {
                    System.out.println("Option 1 \n");
                
                    System.out.println("\nPlease enter Number of Pilots you would like to search:\n");
                    int length=scanner.nextInt();
                    scanner.nextLine();
                    
                    String Pilot_Name_input[]= new String [length];
                    
                    
                    int i;
                    for(i=0; i<length; i++)
                    {
                        System.out.println("\nPlease enter Pilot Names\n");
                        Pilot_Name_input[i]= scanner.nextLine();
                        
                    }
                  
                  
                    System.out.println("Enter the Date ");
                    String str= scanner.nextLine();
                    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = sdf1.parse(str);
                    
                   System.out.println(date);
                  
                    
                    Statement st = con.createStatement();
                    String code="DROP View if exists PilotFlyAssignments ";
                    
                    st.executeUpdate(code);
                    
                    
                    code = "CREATE VIEW PilotFlyAssignments(PilotID, Pilot_Name, Flight_Number,From_City,To_City,Flight_Date) AS SELECT P.ID, P.Name,FLI.FLNO,FL.FromA, FL.ToA, FLI.FDate  FROM Pilot AS P, FlightLegInstance AS FLI, FlightLeg AS FL  WHERE P.ID=FLI.Pilot AND FLI.FLNO=FL.FLNO AND FLI.Seq=FL.Seq ORDER BY P.ID ";
                    st.executeUpdate(code);
                    
                    
                    System.out.println("\n\nDisplaying the PilotFlyAssignments view\n");
                    Statement stmt = con.createStatement();
                    
                    String query = "SELECT * FROM PilotFlyAssignments";
                    ResultSet rs = stmt.executeQuery(query);
                    System.out.println("PilotID\t\tPilot_Name\tFlight_Number\tFrom_City\tTo_City\t\tFlight_Date");
                    
                    
                    while(rs.next()){
                        int PilotID = rs.getInt("PilotID");
                        String Pilot_Name  = rs.getString("Pilot_Name");
                        String Flight_Number  = rs.getString("Flight_Number");
                        String From_City  = rs.getString("From_City");
                        String To_City  = rs.getString("To_City");
                        Date Flight_Date = rs.getDate("Flight_Date");
                        for(i=0; i<length; i++)
                        {
                            if(Pilot_Name_input[i].equals(Pilot_Name) && date.compareTo(Flight_Date)==0 )
                        
                        {
                            System.out.println(PilotID+ "\t\t" + Pilot_Name + "\t\t" + Flight_Number+"\t\t" + From_City + "\t\t" + To_City +"\t\t" + Flight_Date);
                            continue;
                        }
                    }
                    }
                    rs.close();
              break; // Done with the case
                }
                case '2': {

                    // Case 2 for making a leginstance for the pilot
                    System.out.println(" Option 2 \n");
                    Statement st = con.createStatement();
                    
                    int pilotid_input;
                    String FLNO_input;
                    int old_pilotid_input;
                    int Seq_input;
                    String Date_input;
                    
                    System.out.println("Please enter Pilot ID: ");
                    pilotid_input=scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Please enter FLNO");
                    FLNO_input=scanner.next();
                    System.out.println("Please enter Seq");
                    Seq_input=scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Please enter FDate");
                    Date_input=scanner.next();
                    System.out.println("Please enter PilotID to be updated");
                    old_pilotid_input=scanner.nextInt();
                    scanner.nextLine();
                    
                    System.out.println("Before Update");
                    String query = "SELECT * FROM FlightLegInstance";
                    ResultSet rs2 = st.executeQuery(query);
                    while (rs2.next())
                    {
                        System.out.println(rs2.getInt("Seq")+" "+rs2.getString("FLNO")+" "+rs2.getDate("FDate")+" "+rs2.getTime("ActDept")+" "+rs2.getTime("ActArr")+" "+rs2.getInt("Pilot"));
                    }
                    
                    
                    
                    String code= "UPDATE FlightLegInstance SET Pilot=? WHERE FLNO=? AND seq=? AND FDate=? AND Pilot=?";
                    PreparedStatement rs=con.prepareStatement(code);
                    
                    rs.setInt(1,pilotid_input);
                    rs.setString(2,FLNO_input);
                    rs.setInt(3,Seq_input);
                    rs.setString(4,Date_input);
                    rs.setInt(5,old_pilotid_input);
                    
                    rs.executeUpdate();
                    System.out.println("After Update");
                    query = "SELECT * FROM FlightLegInstance";
                    rs2 = st.executeQuery(query);
                    while (rs2.next())
                    {
                        System.out.println(rs2.getInt("Seq")+" "+rs2.getString("FLNO")+" "+rs2.getDate("FDate")+" "+rs2.getTime("ActDept")+" "+rs2.getTime("ActArr")+" "+rs2.getInt("Pilot"));
                    }
                    rs.close();
                    rs2.close();
                    break; // coming out of the loop
                }
                
                 case '3': {
                    // Case 3 for adding a new pilot to the database
                    System.out.println("Option 3 \n");
                    int flag = 0;
                    String name;
                    Statement stmt;
                    String query;
                    ResultSet rs;
                    stmt = con.createStatement();
                    query = "SELECT * FROM Pilot AS P";
                    rs = stmt.executeQuery(query);
                    scanner.nextLine();
                    int Pilot_ID;
                    String Pilot_Name_input_2;
                    int option;
                    int flag2= 0;
                    
                    
                    
                    
                        System.out.println("Enter the ID of the pilot\n");
                        int ID_input=scanner.nextInt();
                        scanner.nextLine();
                        Pilot_ID=ID_input;
                        
                        rs.beforeFirst();  // pointing to the first record    
                        while(rs.next()){
                            if(rs.getInt("ID")==ID_input)
                            {
                                flag=1;
                                System.out.println("ERROR!! There is a pilot with this ID");
                        
                                
                                break; // error found so exit the case
                            }
                        }
                    
                    if(flag==1)
                    {break;}
                   
                        
                        System.out.println("\nPlease enter Pilot Names\n");
                        Pilot_Name_input_2= scanner.nextLine();
                       rs.beforeFirst();  // point the first record
                    
                        while(rs.next())
                        {
                            if(Pilot_Name_input_2.equalsIgnoreCase(rs.getString("Name")))
                        {
                            flag2=1;
                            break;
                            
                        }
                            
    
                        }
                    
                    if(flag2==1)
                    {
                        System.out.println("\nPilot with this Names already exists\n");
                        
                    }
                    
                    else{
                        System.out.println("\nPilot with no duplicate name\n");
                        
                    }
                    
                    System.out.println("Press any number to continue and 9 to quit:");
                        option=scanner.nextInt();
                        scanner.nextLine();
                        
                        if(option==9)
                        {break;}
                        
                        if(option!=9)
                            
                        {  System.out.println("Please input the date for the pilot assignment!");
                            
                            String string_date= scanner.nextLine();
                            
                            
                            Statement st = con.createStatement();
                            String sql =  "INSERT INTO `Pilot` VALUES ("+Pilot_ID+", '"+Pilot_Name_input_2+"','"+string_date+"')";
                            st.executeUpdate(sql);
                            
                            System.out.println("Pilot Added");
                        }
                        
                        
                    
                    Statement st = con.createStatement();
                    query = "SELECT * FROM Pilot";
                    ResultSet rs2 = st.executeQuery(query);
                    while (rs2.next())
                    {
                        System.out.println(rs2.getInt("ID")+" "+rs2.getString("Name")+" "+rs2.getDate("Datehired"));
                    }
                    
                    rs2.close();
                    break;
                }

                // Case 4 to exit the program
               case '4':{System.exit(0);}
                 } 
            
            }
     while(ch !=4);
    }
}


   
		
