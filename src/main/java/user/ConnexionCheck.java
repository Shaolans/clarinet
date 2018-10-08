package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.ConnectionProvider;

public class ConnexionCheck {
		
	public static boolean validate(ConnexionObject obj){
	
		boolean status = false;
		Connection con = ConnectionProvider.getCon();
		
		PreparedStatement pr;
		try {
			pr = con.prepareStatement("select * from user where login=? and password=?");
			pr.setString(1, obj.getLogin());
			pr.setString(2, obj.getPassword());
			
			ResultSet rs = pr.executeQuery();
			
			status = rs.next();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return status;
		
	}

}
