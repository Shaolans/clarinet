package bd;

import java.awt.Image;
import java.awt.image.ImageFilter;
import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.bson.BasicBSONObject;
import org.bson.BsonBinary;
import org.bson.BsonDocument;
import org.bson.BsonInt32;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.bson.Document;
import org.bson.types.Binary;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.bulk.UpdateRequest;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;

import database.MongoDBConnectionProvider;
import database.PostgresqlConnectionProvider;
import opendatasoft.Event;
import opendatasoft.OpendatasoftRequest;

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
		MongoCollection<Document> usersevents = md.getCollection("profiles");
		FindIterable<Document> fid = usersevents.find(
				new BsonDocument("evenements",new BsonString(idevent)));
		for(Document d: fid) {
			list.add(d.getInteger("id"));
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
        	
        	List<String> name = getNameUser(rs.getInt("id_follower"));
        	
        	list.put(rs.getInt("id_follower"), formatName(name));
        	        	
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
        	List<String> name = getNameUser(rs.getInt("id_user"));
        	
        	
        	list.put(rs.getInt("id_user"), formatName(name));
        }
		return list;
	}
	
	/**
	 * 
	 * @param idUser
	 * @return le pseudo, le prénom puis le nom dans une liste
	 * @throws SQLException
	 * @throws NamingException
	 */
	public static List<String> getNameUser(int idUser) throws SQLException, NamingException{
		
		Connection postgre = PostgresqlConnectionProvider.getCon();
		PreparedStatement pr = postgre.prepareStatement("SELECT login, fname, lname FROM USERS WHERE id = ?"); 
		pr.setInt(1, idUser);
        ResultSet rs = pr.executeQuery();
        List<String> name = new ArrayList<String>();
        
        if(rs.next()) {
        	
        	
        	name.add(rs.getString(1));
        	name.add(rs.getString(2));
        	name.add(rs.getString(3));
        	
        }
        
        
		return name;
	}
	
	public static boolean verifSessionOK(HttpSession session){
		if(session==null || session.getAttribute("id_user")==null){
			
			return false;
		}
		
		return true;
	}
	

	
	
	
	public static void main(String[] args) throws UnknownHostException {
		System.out.println(getParticipants("e397f4c3b8213dbf75b96f91992d4b87ea76b3d1"));
	}

	public static String getNom(int idUser) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static String formatName(List<String> name){
		StringBuilder sb = new StringBuilder();
    	sb.append(name.get(0));
    	
    	switch(name.size()){
        	case 2:
        		sb.append(" (");
        		sb.append(name.get(1));
        		sb.append(")");
        		break;
        	case 3:
        		sb.append(" (");
        		sb.append(name.get(1));
        		sb.append(" ");
        		sb.append(name.get(2));
        		sb.append(")");
        		
        		break;
    		
    	}
    	
    	return sb.toString();
	}
	
	
	/**
	 * Renvoie en position 0 la liste des évéments passés
	 * et en position 1 la liste des évéments futurs
	 * @param id_user
	 * @return
	 */
	public static List<List<Event>> getEvenements(int id_user){
		
		List<List<Event>> evenements = new ArrayList<List<Event>>();
		
		List<Event> evenements_futurs = new ArrayList<Event>();
		List<Event> evenements_passes = new ArrayList<Event>();
		
		evenements.add(evenements_passes);
		evenements.add(evenements_futurs);
		
		
		try {
			MongoDatabase db = MongoDBConnectionProvider.getDB();
			
			MongoCollection<Document> col = db.getCollection("profiles");
			
			FindIterable<Document> docs = col.find();
			Document curr = null;
			
			for(Document doc : docs){
				
				Integer id = (Integer)doc.get("id");
				if(id==id_user){
					curr = doc;
					break;
				}
			}
			if(curr !=null){
				ArrayList<String> id_evenements = (ArrayList<String>) curr.get("evenements");
				for(String id : id_evenements){
					Event e;
					
					try {
						e = OpendatasoftRequest.eventById(id);
						
						if(e==null) continue;
						
						String date_event = e.getDatestart();
						
						String[] splittedDate = date_event.split("-");
						
						Date currentDate = new Date();
						
						Calendar calendar = Calendar.getInstance();
						
						calendar.setTime(currentDate);
						
						if(calendar.get(Calendar.YEAR)>=Integer.valueOf(splittedDate[0])){
							evenements_passes.add(e);
						}
						else{
							if(calendar.get(Calendar.YEAR)==Integer.valueOf(splittedDate[0])){
								if(calendar.get(Calendar.MONTH)+1>Integer.valueOf(splittedDate[1])){
									evenements_passes.add(e);
								}
								
								else{
									
									if(calendar.get(Calendar.MONTH)+1==Integer.valueOf(splittedDate[1])){
										if(calendar.get(Calendar.DAY_OF_MONTH)>=Integer.valueOf(splittedDate[2])){
											evenements_passes.add(e);
										}
										else{
											evenements_futurs.add(e);
										}
										
										
									}
									else{
										evenements_futurs.add(e);
									}
									
									
								}
							}
							else{
								evenements_futurs.add(e);
							}
						}
					
					
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return evenements;
	}
	
	
	/**
	 * Upload l'image de profile du user dans mongoDB
	 * @param image
	 */
	public static void uploadImage(int id_user, byte[] image, String type){
		try {
			MongoDatabase db = MongoDBConnectionProvider.getDB();
			
			MongoCollection<Document> col = db.getCollection("profiles");
			
			Document curr = col.find(new BsonDocument("id", new BsonInt32(id_user))).first();
			if(curr!=null){
				col.updateOne(new BsonDocument("id", new BsonInt32(id_user)),
						new BsonDocument("$set", new BsonDocument("image.photo", new BsonBinary(image))));
				
				col.updateOne(new BsonDocument("id", new BsonInt32(id_user)),
						new BsonDocument("$set", new BsonDocument("image.type", new BsonString(type))));
				
			}
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static  List<Object> getImageAndType(int id_user){
		
		 List<Object> res=new ArrayList<Object>();
		
		try {
			MongoDatabase db = MongoDBConnectionProvider.getDB();
			
			MongoCollection<Document> col = db.getCollection("profiles");
			
			Document curr = col.find(new BsonDocument("id", new BsonInt32(id_user))).first();
			
			
			
			if(curr!=null){
				
				Document imageDoc = (Document)curr.get("image");
				if(imageDoc!=null){
					res.add(((Binary) imageDoc.get("photo")).getData());
					res.add(imageDoc.getString("type"));
				}
				
				
			}
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
		
	}

	public static void changerBio(int id_user, String msg) {
		try {
			MongoDatabase db = MongoDBConnectionProvider.getDB();
			
			MongoCollection<Document> col = db.getCollection("profiles");
			
			Document curr = col.find(new BsonDocument("id", new BsonInt32(id_user))).first();
			
			if(curr!=null){
				col.updateOne(new BsonDocument("id", new BsonInt32(id_user)),
						new BsonDocument("$set", new BsonDocument("bio", new BsonString(msg))));
				
			}
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static String getBio(int id_user) {
		
		String msg = "";
		
		try {
			MongoDatabase db = MongoDBConnectionProvider.getDB();
			
			MongoCollection<Document> col = db.getCollection("profiles");
			
			Document curr = col.find(new BsonDocument("id", new BsonInt32(id_user))).first();
			
			
			if(curr!=null){
				msg = (String) curr.get("bio");
				
			}
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return msg;
	}
	
	
	public static boolean addEventParticipation(int id_user, String id_event){
		
		MongoDatabase md;
		try {
			md = MongoDBConnectionProvider.getDB();
			MongoCollection<Document> col = md.getCollection("profiles");
			Document d = col.find(new BsonDocument("id", new BsonInt32(id_user))).first();
			System.out.println(id_user);
			if(d != null) {
				
				ArrayList<String> evenements = (ArrayList<String>)d.get("evenements");
				
				for(String event : evenements){
					if(event.equals(id_event))
						return false;
				}
				
				col.updateOne(new BsonDocument("id", new BsonInt32(id_user)),
						new BsonDocument("$push", new BsonDocument("evenements", new BsonString(id_event))));

			}
			else{
				System.out.println("ICI");
				return false;
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
		
	}
	
	
	public static boolean supprimerEventParticipation(int id_user, String id_event){
		MongoDatabase md;
		try {
			md = MongoDBConnectionProvider.getDB();
			MongoCollection<Document> col = md.getCollection("profiles");
			Document d = col.find(new BsonDocument("id", new BsonInt32(id_user))).first();
			
			if(d != null) {
				ArrayList<String> evenements = (ArrayList<String>)d.get("evenements");
				String e=null;
				for(String event : evenements){
					if(event.equals(id_event)){
						e = event;
					}
				}
				
				if(e==null) return false;
				
				col.updateOne(new BsonDocument("id", new BsonInt32(id_user)),
						new BsonDocument("$pull", new BsonDocument("evenements", new BsonString(id_event))));

			}
			else
				return false;
			
			
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	
	public static boolean getEvenementId(int id_user,String id_event){
		MongoDatabase md;
		try {
			md = MongoDBConnectionProvider.getDB();
			MongoCollection<Document> col = md.getCollection("profiles");
			Document d = col.find(new BsonDocument("id", new BsonInt32(id_user))).first();
			
			if(d != null) {
				
				ArrayList<String> events = (ArrayList<String>)d.get("evenements");
				
				for(String event : events){
					if(event.equals(id_event))
						return true;
				}
				
				return false;
			}
			else
				return false;	
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
		
		
	}

	

}
