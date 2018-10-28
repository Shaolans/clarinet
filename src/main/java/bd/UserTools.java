package bd;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import database.MongoDBConnectionProvider;
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
	
	
	public static List<Integer> getParticipants(String idevent) throws UnknownHostException{
		List<Integer> list = new ArrayList<Integer>();
		MongoDatabase md = MongoDBConnectionProvider.getDB();
		MongoCollection<Document> usersevents = md.getCollection("users_events");
		FindIterable<Document> fid = usersevents.find(new Document().append("events", new Document("$elemMatch", new Document("id_event", idevent))));
		for(Document d: fid) {
			list.add(d.getInteger("id_user"));
		}
		return list;
	}
	
	
	public static Map<Integer, String> getFollowsName(int idUser) throws SQLException, NamingException{
		Map<Integer, String> list = new HashMap<Integer, String>();
		Connection postgre = PostgresqlConnectionProvider.getCon();
        PreparedStatement pr = postgre.prepareStatement("SELECT id_follower FROM FOLLOWERS WHERE id_user = ?");
        pr.setInt(1, idUser);
        ResultSet rs = pr.executeQuery();
        
       
        
        while(rs.next()) {
        	 String name = getNameUser(rs.getInt("id_follower"));
        	list.put(rs.getInt("id_follower"), name);
        	
        }
		return list;
	}
	
	public static Map<Integer, String> getFollowersName(int idUser) throws SQLException, NamingException{
		Map<Integer, String> list = new HashMap<Integer, String>();
		Connection postgre = PostgresqlConnectionProvider.getCon();
		PreparedStatement pr = postgre.prepareStatement("SELECT id_user FROM FOLLOWERS WHERE id_follower = ?");   
		pr.setInt(1, idUser);
        ResultSet rs = pr.executeQuery();
        
        while(rs.next()) {
        	String name = getNameUser(rs.getInt("id_user"));
        	list.put(rs.getInt("id_user"), name);
        }
		return list;
	}
	
	
	public static String getNameUser(int idUser) throws SQLException, NamingException{
		Map<Integer, String> list = new HashMap<Integer, String>();
		Connection postgre = PostgresqlConnectionProvider.getCon();
		PreparedStatement pr = postgre.prepareStatement("SELECT fname, lname FROM USERS WHERE id = ?"); 
		pr.setInt(1, idUser);
        ResultSet rs = pr.executeQuery();
        String name = null;
        if(rs.next()) {
        	
        	name = rs.getString(2)+" "+rs.getString(1);
        	
        	System.out.println(name);
        }
        
        
		return name;
	}
	
	public static boolean verifSessionOK(HttpSession session, HttpServletResponse response){
		if(session==null){
			try {
				response.sendRedirect("/connexion/connexion.jsp");
				return false;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}
	

	
	
	
	public static void main(String[] args) throws UnknownHostException {
		System.out.println(getParticipants("e397f4c3b8213dbf75b96f91992d4b87ea76b3d1"));
	}
	
	
	
	
	

}
