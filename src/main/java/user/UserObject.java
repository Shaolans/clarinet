package user;

import java.util.HashMap;
import java.util.Map;

public class UserObject {
	
	private int idUser;
	private Map<Integer, String> abonnes = new HashMap<Integer, String>();
	private Map<Integer, String> abonnements = new HashMap<Integer,String>();
	
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
	
	

}
