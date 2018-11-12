package authentification;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.print.DocFlavor.BYTE_ARRAY;

import database.utils.UserTools;
import opendatasoft.Event;

public class UserObject {
	
	private int idUser;
	private String login, nom, prenom, formattedName;
	private Map<Integer, String> abonnes = new HashMap<Integer, String>();
	private Map<Integer, String> abonnements = new HashMap<Integer,String>();
	private List<Event> evenementsPasses, evenementsFuturs;
	private String bio;
	private byte[] image;
	
	public UserObject(int idUser){
		
		this.idUser = idUser;
		
		
		try {
			abonnes = UserTools.getFollowersName(idUser);
			abonnements = UserTools.getFollowsName(idUser);
			nom = UserTools.getNom(idUser);
			List<String> noms = UserTools.getNameUser(idUser);
			
			formattedName = UserTools.formatName(noms);
			
			login = noms.get(0);
			prenom = noms.get(1);
			nom = noms.get(2);
			
			List<List<Event>> evenements = UserTools.getEvenements(idUser);
			
			evenementsPasses = evenements.get(0);
			evenementsFuturs = evenements.get(1);
			
			bio = UserTools.getBio(idUser);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void setIdUser(int idUser){
		this.idUser = idUser;
	}
	
	public void setAbonnes(Map<Integer, String> abonnes){
		this.abonnes = abonnes;
	}
	
	public void setAbonnements(Map<Integer, String> abonnements){
		this.abonnements = abonnements;
	}

	public int getIdUser() {
		return idUser;
	}

	public Map<Integer, String> getAbonnes() {
		return abonnes;
	}

	public Map<Integer, String> getAbonnements() {
		return abonnements;
	}
	
	public String getFormattedName(){
		return formattedName;
	}
	
	
	public String getNom(){
		return nom;
	}
	
	public String getPrenom(){
		return prenom;
	}
	
	public String getLogin(){
		return login;
	}
	
	public List<Event> getEvenementsPasses(){
		return evenementsPasses;
	}
	
	public List<Event> getEvenementsFuturs(){
		return evenementsFuturs;
	}
	
	public String getBio(){
		return bio;
	}
	

}
