package authentification;

import java.sql.SQLException;

import javax.naming.NamingException;

import database.utils.UserTools;

public class UserCheck {
	
	public static void chargerUser(UserObject user){
		try {
			
			user.setAbonnes(UserTools.getFollowersName(user.getIdUser()));
			user.setAbonnements(UserTools.getFollowsName(user.getIdUser()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
