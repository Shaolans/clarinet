package chat;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.bson.Document;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;

import database.utils.UsersMessageTools;


@ServerEndpoint(value = "/chat")
public class ChatWebSocket {
	private Gson gson = new Gson();
	private Logger log = Logger.getLogger(ChatWebSocket.class.getSimpleName());
	private static final Map<String, ChatRoom> rooms = new HashMap<String, ChatRoom>();
	private static final Map<String, Session> onlineSessions = new HashMap<String, Session>();
	
	@OnOpen
	public void onOpen(Session session) {}
	
	@OnMessage
	public void onMessage(Session session, String JSONmsg){
		
		Message message = gson.fromJson(JSONmsg, Message.class);
		String strMsg = message.getContent();
		String strFrom = message.getFrom();
		String strFrom_id = message.getFrom_id();
		String strTo = message.getTo();
		String strTo_id = message.getTo_id();
		String strType = message.getType();
		String strTime = message.getTime();
		// verifier qu'on recoit les bonnes informations
//		log.info(strMsg);
//		log.info(strFrom);
//		log.info(strFrom_id);
//		log.info(strTo);
//		log.info(strTo_id);
//		log.info(strType);
//		log.info(strTime);
		
		if(strType.equals("room")) {
			sendRoomMessage(strTo_id, strMsg, strTime, session);
		}
		else if (strType.equals("private")) {
			sendPrivateMessage(strTo, strTo_id, strMsg, strTime, session);
		}
		else if (strType.equals("joinroom")) {
			joinRoom(strTo, strTo_id, strMsg, strTime, session);
		}
		else if (strType.equals("leaveroom")) {
			leaveRoom(strTo_id, strMsg, strTime, session);
		}
		else if (strType.equals("login")) {
			login(strFrom, strFrom_id, session);
		}
		else {
			log.info("Error: Invalid message type");
		}
	}
	
	private void login(String user_name, String user_id, Session session) {
		session.getUserProperties().put("id", user_id);
		session.getUserProperties().put("name", user_name);
		onlineSessions.put(user_id, session);
	}
	
	private void leaveRoom(String room_id, String msg, String time, Session session) {
		ChatRoom room = rooms.get(room_id);
		if(room == null) {
			log.info("Error: Try to leave inexist room");
		}
		room.systemMessage(msg, time);
		room.leave(session);
	}
	
	private void joinRoom(String room_name, String room_id, String msg, String time, Session session) {
		ChatRoom room = rooms.get(room_id);
		if(room == null) {
			room = new ChatRoom(room_id, room_name);
			rooms.put(room_id, room);
		}
		room.join(session);
		room.systemMessage(msg, time);
	}
	
	private void sendRoomMessage(String room_id, String msg, String time, Session session) {
		// TODO: remote msg to dataBase
		ChatRoom room = rooms.get(room_id);
		room.sendMessageToRoom(msg, time, session);
	}
	
	private void sendPrivateMessage(String to_user, String to_user_id, String msg, String time, Session session) {
		int id = Integer.parseInt((String)session.getUserProperties().get("id"));
		String name = (String)session.getUserProperties().get("name");
		// database
//		try {
//			Document msg_doc = UsersMessageTools.makeMessage(time, id, msg);
//			UsersMessageTools.addMessage(id, Integer.parseInt(to_user_id), msg_doc);
//		} catch (NumberFormatException e1) {
//			e1.printStackTrace();
//		} catch (UnknownHostException e1) {
//			e1.printStackTrace();
//		}
		Session adverse = onlineSessions.get(to_user_id);
		
		Message message = new Message();
		message.setContent(msg);
		message.setFrom(name);
		message.setFrom_id(id+"");
		message.setTo(to_user);
		message.setTo_id(to_user_id);
		message.setType("private");
		message.setTime(time);
		
		if(adverse != null) {
			try {
				adverse.getBasicRemote().sendText(gson.toJson(message));
			} catch (IOException e) {
				log.info("Error: "+e.getMessage());
			}
		}
	}

	@OnClose
	public void onClose(Session session){
		onlineSessions.remove(session.getUserProperties().get("id"));
	}
	
	@OnError
	public void onError(Session session, Throwable ex) {
	}
}
