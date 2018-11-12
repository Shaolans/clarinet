package chat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.Session;

import com.google.gson.Gson;

public class ChatRoom {
	private String id = null;
	private String name = null;
	private Gson gson = new Gson();
	private List<Session> sessions = new ArrayList<Session>();
	
	public ChatRoom(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	// synchronized car il y aura plusieurs utilisateurs dans un chatroom
	public synchronized void join(Session session) {
		sessions.add(session);
	}
	
	public synchronized void leave(Session session) {
		sessions.remove(session);
	}
	
	public synchronized void sendMessageToRoom(String message, String time, Session sender) {
		Message msg = new Message();
		msg.setContent(message);
		msg.setFrom((String)sender.getUserProperties().get("name"));
		msg.setFrom_id((String)sender.getUserProperties().get("id"));
		msg.setTo(this.name);
		msg.setTo_id(this.id);
		msg.setType("room");
		msg.setTime(time);
		for(Session session: sessions) {
			if(session.isOpen() && (String)session.getUserProperties().get("id")!=(String)sender.getUserProperties().get("id")) {
				try {
					session.getBasicRemote().sendText(gson.toJson(msg));
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public synchronized void systemMessage(String message, String time) {
		Message msg = new Message();
		msg.setContent(message);
		msg.setFrom("");
		msg.setFrom_id("");
		msg.setTo(this.name);
		msg.setTo_id(this.id);
		msg.setType("room");
		msg.setTime(time);
		for(Session session: sessions) {
			if(session.isOpen()) {
				try {
					session.getBasicRemote().sendText(gson.toJson(msg));
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public synchronized List<Session> getUserList() {
		return sessions;
	}

	public synchronized String getId() {
		return id;
	}
	
	public synchronized String getName() {
		return name;
	}
}
