package clarinet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import database.PostgresqlConnectionProvider;

public class MiseajourBD {
	
	public static void main(String[] args){
		
		try {
			Connection con = PostgresqlConnectionProvider.getCon();
			String request = "Select * from user";
			PreparedStatement pr = con.prepareStatement(request);
			ResultSet rs = pr.executeQuery();
			
			System.out.println(rs.toString());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
