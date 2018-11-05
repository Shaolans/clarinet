package chat;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.mongodb.BasicDBObject;
import com.mongodb.BulkWriteOperation;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import database.PostgresqlConnectionProvider;

@WebServlet(
		name = "Chat",
		urlPatterns = "/chat")
public class ChatServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	//send msg
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// DB: id_chat dialog_hist
		String dialog_id = request.getParameter("dialog_id");
		String user_id = request.getParameter("user_id");
		String msg_to_send = request.getParameter("msg_to_send");
		JSONArray msg = new JSONArray();
		msg.put(user_id);
		msg.put(msg_to_send);
		
		Connection postgre;
		try {
			postgre = PostgresqlConnectionProvider.getCon();
			PreparedStatement pr = postgre.prepareStatement("SELECT msg FROM DIALOG WHERE dialog_id = ?");
			pr.setInt(1, Integer.parseInt(dialog_id));
			ResultSet rs = pr.executeQuery();
			JSONArray jarray = new JSONArray(rs.getString("msg"));
			jarray.put(msg.toString());
			// replaceOne()
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//chercher l historique des dialogues
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// DB: id_chat dialog_hist
		String dialog_id = request.getParameter("dialog_id");
		ServletOutputStream out = response.getOutputStream();
		try {
			// une autre collection pour les dialogues
			Connection postgre = PostgresqlConnectionProvider.getCon();
			PreparedStatement pr = postgre.prepareStatement("SELECT msg FROM DIALOG WHERE dialog_id = ?");
			pr.setInt(1, Integer.parseInt(dialog_id));
			ResultSet rs = pr.executeQuery();
			JSONArray jarray = new JSONArray(rs.getString("msg")); // [ [id_user,msg], [id_user,msg] ...]
			for (int i =0; i<jarray.length(); i++){
                JSONArray jarr = new JSONArray(jarray.get(i)+"");
                out.print(
	                " <div class=\"chatbox-message\">\n "
                   +" <span class=\"message-from\">"+jarr.get(0)+"</span>\n"
                   +" <span class=\"message-content\">"+jarr.get(1)+"</span>\n"
                   +" </div>");
			}
			out.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// replace one de mongoDB
	public boolean replaceOne(MongoClient mg, String dialog_id, String msg) {
		DB db = mg.getDB("clarinet");
		DBCollection coll = db.getCollection("dialog");
		BulkWriteOperation b1 = coll.initializeUnorderedBulkOperation();
		BasicDBObject query = new BasicDBObject();
		query.append("$setOnInsert",
				new BasicDBObject("dialog_id",dialog_id)
					.append("msg", msg));
		b1.find(new BasicDBObject("dialog_id",dialog_id)).upsert().updateOne(query);
		b1.execute();
		
		return true;
	}
}
