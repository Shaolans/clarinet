package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.NamingException;

public class PostgresqlConnectionProvider {  
	private static Connection con=null;  
	
	
	  
	public static Connection getCon() throws SQLException, NamingException{  
		
		if(con==null){
			
			con = DriverManager.getConnection(Database.POSTGRESQL_URL);
			
		}
	    return con;  
	}  
  
}  