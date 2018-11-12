package database.utils;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;

import database.MongoDBConnectionProvider;

public class UsersMessageTools {
	
	public static void main(String[] args) throws UnknownHostException {
		/*System.out.println(makeMessage(new Date().toString(), 1, "Salut c'est user1"));
		addMessage(1, 2, makeMessage(new Date().toString(), 1, "Salut c'est user1"));
		addMessage(1, 2, makeMessage(new Date().toString(), 2, "Salut c'est user2 !"));
		addMessage(2, 1, makeMessage(new Date().toString(), 2, "Test3 !!!"));
		System.out.println(getMessages(1, 2));
		System.out.println(getMessages(2, 1));*/
		
		for(Document d: MongoDBConnectionProvider.getDB().getCollection("friends_msg").find()) {
			System.out.println(d);
		}
		
	}
	public static Document makeMessage(String timestamp, int sender, String content) {
		Document message = new Document("timestamp", timestamp)
				.append("sender", sender)
				.append("content", content);
		return message;
	}
	
	public static Document findConversation(int user1, int user2) throws UnknownHostException {
		MongoDatabase md = MongoDBConnectionProvider.getDB();
		MongoCollection<Document> friends_msg = md.getCollection("friends_msg");
		Document d = friends_msg.find(new Document("user1", user1).append("user2", user2)).first();
		if(d == null) {
			return friends_msg.find(new Document("user1", user2).append("user2", user1)).first();
		}
		return d;
	}
	
	
	public static List<Document> getMessages(int user1, int user2) throws UnknownHostException{
		Document d = findConversation(user1, user2);
		if(d == null) return null;
		List<Document> messages = (List<Document>)d.get("messages");
		return messages;
	}
	
	public static void addMessage(int user1, int user2, Document message) throws UnknownHostException {
		MongoDatabase md = MongoDBConnectionProvider.getDB();
		MongoCollection<Document> friends_msg = md.getCollection("friends_msg");
		Document d = findConversation(user1, user2);
		if(d == null) {
			d = new Document("user1", user1)
					.append("user2", user2);
			List<Document> messages = new ArrayList<Document>();
			messages.add(message);
			d.append("messages", messages);
			friends_msg.insertOne(d);
			return;
		}
		List<Document> messages = (List<Document>)d.get("messages");
		messages.add(message);
		UpdateResult res = friends_msg.updateOne(new Document("user1", d.get("user1")).append("user2", d.get("user2")) ,
				new Document("$set", new Document("messages", messages)));
		return;
	}
	
	
}
