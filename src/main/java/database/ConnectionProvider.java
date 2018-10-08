package database;

import java.sql.Connection;
import java.sql.DriverManager;

import database.Database;

public class ConnectionProvider {  
	private static Connection con=null;  
	
	static{  
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			con=DriverManager.getConnection(Database.CONNECTION_URL,Database.USERNAME,Database.PASSWORD);  
		}catch(Exception e){}  
	}  
	  
	public static Connection getCon(){  
	    return con;  
	}  
  
}  