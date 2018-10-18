package bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import database.PostgresqlConnectionProvider;

public class UserTools {
	
	public static boolean checkSession(int id, String key){
		
		boolean status = true;
		
		return status;
	}
	
	//personne que idUser suit
	public static List<Integer> getFollows(int idUser) throws SQLException, NamingException{
		List<Integer> list = new ArrayList<Integer>();
		Connection postgre = PostgresqlConnectionProvider.getCon();
        PreparedStatement pr = postgre.prepareStatement("SELECT id_follower FROM FOLLOWERS WHERE id_user = ?");
        pr.setInt(1, idUser);
        ResultSet rs = pr.executeQuery();
        while(rs.next()) {
        	list.add(rs.getInt("id_follower"));
        }
		return list;
	}
	
	//personne qui suit idUser
	public static List<Integer> getFollowers(int idUser) throws SQLException, NamingException{
		List<Integer> list = new ArrayList<Integer>();
		Connection postgre = PostgresqlConnectionProvider.getCon();
        PreparedStatement pr = postgre.prepareStatement("SELECT id_user FROM FOLLOWERS WHERE id_follower = ?");
        pr.setInt(1, idUser);
        ResultSet rs = pr.executeQuery();
        while(rs.next()) {
        	list.add(rs.getInt("id_user"));
        }
		return list;
	}
	
	
	
	
	

}
