import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class createDatabase {

	public static final String PORT_NUMBER = "8889";
	/**
	 * Creates a database for us to work with 
	 *
	 */
	public Connection createDatabase() {
		try (
				// Step 1: Allocate a database "Connection" object
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:" + PORT_NUMBER + "/", 
						"root", "root"); // MySQL
				// Step 2: Allocate a "Statement" object in the Connection
				Statement stmt = conn.createStatement();
				) {
			// Step 3 - create our database
			String sql = "CREATE DATABASE IF NOT EXISTS experiences";
			stmt.execute(sql);
			return conn;
			

		} catch(SQLException ex) {
			ex.printStackTrace();
			return null; }
	}
		
	/**
	 * creates a table in our database, adds the title "question x" to each 
	 * 
	 *
	 */
		
	public Connection createTable() {
		try (
				// Step 1: Allocate a database "Connection" object
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:" + PORT_NUMBER + "/experiences?user=root&password=root"); // MySQL
				// Step 2: Allocate a "Statement" object in the Connection
				Statement stmt = conn.createStatement();
				) {
			// Step 3 - create our database
			String sql2 = "CREATE TABLE IF NOT EXISTS t1 ( " +
					"question1 varchar(500), " +
					"question2 varchar(500), " +
					"question3 varchar(500), " +
					"question4 varchar(500), " +
					"question5 varchar(500), " +
					"question6 varchar(500), " +
					"question7 varchar(500), " +
					"question8 varchar(500), " +
					"question9 varchar(500));";
			stmt.execute(sql2);
			return conn;
			
			

		} catch(SQLException ex) {
			ex.printStackTrace();
			return null;}
		}
			
	/**
	 * imports a csv excel file to the table in our database 
	 *
	 */
	public void importData(Connection conn,String filename)
    {
		
        Statement stmt;
        String query;
        try {
				// Step 1: Allocate a database "Connection" object
				conn = DriverManager.getConnection("jdbc:mysql://localhost:" + PORT_NUMBER + "/Experiences?user=root&password=root"); // MySQL
				// Step 2: Allocate a "Statement" object in the Connection
				 stmt = conn.createStatement();
				 {
			

		} }catch(SQLException ex) {
			ex.printStackTrace();
			
		}
        
    
        try
        {
            stmt = conn.createStatement(
    ResultSet.TYPE_SCROLL_SENSITIVE,
    ResultSet.CONCUR_UPDATABLE);
 
            
            query = "LOAD DATA LOCAL INFILE '"+filename+"' INTO TABLE t1  FIELDS TERMINATED BY ',' (question1,question2,question3,question4,question5,question6,question7,question8,question9)";
 
            stmt.executeUpdate(query);
                 
        }
        catch(Exception e)
        {
            e.printStackTrace();
            stmt = null;
        }
    }

	/**
	 * calls the above methods to run all the code that creates a database, table, and popoulates a database 
	 *
	 */
	public static void main(String[] args) {
		createDatabase dbCreator = new createDatabase();
		Connection conn = dbCreator.createDatabase();
		dbCreator.createTable();
		String filename = "Summer expereince survey 2016 for oliver.csv"; 
		dbCreator.importData(conn, filename);
		
		
}
	}