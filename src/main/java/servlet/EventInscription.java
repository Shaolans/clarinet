package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;
import org.json.JSONArray;
import org.json.JSONObject;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.UpdateResult;

import database.MongoDBConnectionProvider;
@WebServlet(
		name = "EventInscription", 
        urlPatterns = {"/event/participate"}
)
public class EventInscription extends HttpServlet {
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
		MongoDatabase md = MongoDBConnectionProvider.getDB();
		String user = ""+req.getSession(false).getAttribute("id_user");
		String idevent = req.getParameter("id_event");
		MongoCollection<Document> usersevents = md.getCollection("users_events");
		Document d = usersevents.find(Filters.eq("id_user", user)).first();
		JSONObject answer = new JSONObject();
		answer.append("id_user", user);
		answer.append("id_event", idevent);
		if(d != null) {
			List<Document> listevents = (List<Document>)d.get("events");
			
			for(int i = 0; i < listevents.size(); i++) {
				Document obj = listevents.get(i);
				if(obj.getString("id_event").equals(idevent)) {
					resp.setContentType("application/json");
					answer.append("resp", "fail");
					resp.getWriter().write(answer.toString());
					return;
				}
			}
			listevents.add(new Document().append("id_event", idevent).append("passed", false));
			
			UpdateResult res = usersevents.updateOne(new Document("id_user", user) ,
					new Document("$set", new Document("events", d)));
			
			answer.append("resp", "success");
		}else {
			d = new Document().append("id_user", user);
			List<Document> listevents = new ArrayList<Document>();
			listevents.add(new Document().append("id_event", idevent).append("passed", false));
			d.append("events", listevents);
			usersevents.insertOne(d);
			answer.append("resp", "success");
		}
		
		resp.setContentType("application/json");
		resp.getWriter().write(answer.toString());

	}
}
