package database;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.Mongo;

public class MongoDBConnectionProvider {
	
	private static DB db=null;  
	
	
	  
	@SuppressWarnings("deprecation")
	public static DB getDB() throws UnknownHostException{
		
		if(db==null){
			if(db==null){
				Mongo m = new Mongo(Database.MONGO_URL, Database.MONGO_PORT);
				db = m.getDB(Database.MONGO_DB);
			}
		}
		
		return db;
	}

}
