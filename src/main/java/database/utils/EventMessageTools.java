package database.utils;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;

import database.MongoDBConnectionProvider;

public class EventMessageTools {
	
	public static void main(String[] args) throws UnknownHostException {
		addEventMessage("testeventid", UsersMessageTools.makeMessage(new Date().toString(), 1, "Test1"));
		System.out.println(getEventMessages("testeventid"));
		addEventMessage("testeventid", UsersMessageTools.makeMessage(new Date().toString(), 1, "Test2"));
		System.out.println(getEventMessages("testeventid"));
	}
	
	public static Document findEventChat(String idevent) throws UnknownHostException {
		MongoDatabase md = MongoDBConnectionProvider.getDB();
		MongoCollection<Document> friends_msg = md.getCollection("events_msg");
		return friends_msg.find(new Document("id_event", idevent)).first();
	}
	
	
	public static List<Document> getEventMessages(String idevent) throws UnknownHostException{
		Document d = findEventChat(idevent);
		if(d == null) return null;
		List<Document> messages = (List<Document>)d.get("messages");
		return messages;
	}
	
	
	public static void addEventMessage(String idevent, Document message) throws UnknownHostException {
		MongoDatabase md = MongoDBConnectionProvider.getDB();
		MongoCollection<Document> events_msg = md.getCollection("events_msg");
		Document d = findEventChat(idevent);
		if(d == null) {
			d = new Document("id_event", idevent);
			List<Document> messages = new ArrayList<Document>();
			messages.add(message);
			d.append("messages", messages);
			events_msg.insertOne(d);
			return;
		}
		List<Document> messages = (List<Document>)d.get("messages");
		messages.add(message);
		UpdateResult res = events_msg.updateOne(new Document("id_event", idevent) ,
				new Document("$set", new Document("messages", messages)));
		return;
	}
}
