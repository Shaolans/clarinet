package bd;

import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

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
			list.add(Integer.parseInt(d.getString("id_user")));
		}
		return list;
	}
	
	
	public static void main(String[] args) throws UnknownHostException {
		System.out.println(getParticipants("e397f4c3b8213dbf75b96f91992d4b87ea76b3d1"));
	}
	
	
	
	
	

}
