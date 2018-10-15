package user;

import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.mongodb.DB;

import database.MongoDBConnectionProvider;
import database.PostgresqlConnectionProvider;

public class ConnexionCheck {
		
	public static int validate(ConnexionObject obj) throws SQLException, NamingException, UnknownHostException{
	
		boolean status = false;
		int id = -1;
		
		
		Connection con = PostgresqlConnectionProvider.getCon();
		
		PreparedStatement pr;
		try {
			pr = con.prepareStatement("select * from users where login=? and password=crypt(?, password)");
			pr.setString(1, obj.getLogin());
			pr.setString(2, obj.getPassword());
			
			ResultSet rs = pr.executeQuery();
			
			status = rs.next();
			
			if(status){
				
				id = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return id;
		
	}

}
