package database;

import java.net.UnknownHostException;


import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

public class MongoDBConnectionProvider {
	
	private static MongoDatabase db=null;  
	
	
	  

	@SuppressWarnings("resource")
	public static MongoDatabase getDB() throws UnknownHostException{
		if(db == null) {
			MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://"+Database.MONGO_USERNAME
					+":"+Database.MONGO_MDP
					+"@"+Database.MONGO_URL
					+":"+Database.MONGO_PORT
					+"/"+Database.MONGO_DB));

			db = mongoClient.getDatabase(Database.MONGO_DB);
		}

		return db;
	}

}
