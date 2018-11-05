package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.PostgresqlConnectionProvider;

@WebServlet(
		name = "Inscription",
		urlPatterns = "/inscription")
public class Inscription extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_first_name = request.getParameter("user_first_name");
		String user_last_name = request.getParameter("user_last_name");
		String user_pseudo = request.getParameter("user_pseudo");
		String user_pwd = request.getParameter("user_pwd");
		String user_confirm_pwd = request.getParameter("user_confirm_pwd");
		String user_email = request.getParameter("user_email");
		
		Map<String,String> erreurs = new HashMap<String, String>();
		
		try {
			validationNom(user_first_name);
		} catch (Exception e) {
			erreurs.put("nom", e.getMessage());
		}
		
		try {
			validationPrenom(user_last_name);
		} catch (Exception e) {
			erreurs.put("prenom", e.getMessage());
		}
		
		try {
			validationPseudo(user_pseudo);
		} catch (Exception e) {
			erreurs.put("pseudo", e.getMessage());
		}
		
		try {
			validationMdp(user_pwd);
		} catch (Exception e) {
			erreurs.put("pwd", e.getMessage());
		}
		
		try {
			validationConfirmMdp(user_pwd, user_confirm_pwd);
		} catch (Exception e) {
			erreurs.put("confirm", e.getMessage());
		}
		
		try {
			validationEmail(user_email);
		} catch (Exception e) {
			erreurs.put("email", e.getMessage());
		}
		
		if( erreurs.isEmpty() ) {
			
			Connection postgre;
			try {
				postgre = PostgresqlConnectionProvider.getCon();
				PreparedStatement pr = postgre.prepareStatement("INSERT INTO USERS(login,password,fname,lname,mail) VALUES (?,crypt(?,gen_salt('bf')),?,?,?)");
				pr.setString(1, user_pseudo);
		        pr.setString(2, user_pwd);
		        pr.setString(3, user_first_name);
		        pr.setString(4, user_last_name);
		        pr.setString(5, user_email);
		        pr.executeUpdate();
		        pr.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
			
			
			request.setAttribute("resultat", "Inscription réussi");
			this.getServletContext().getRequestDispatcher("/").forward(request, response);
		}
		else {
			// Stockage du résultat et des messages d'erreur dans l'objet request 
			request.setAttribute("erreurs", erreurs);
			// Transmission de la paire d'objets request/response à notre JSP
			this.getServletContext().getRequestDispatcher("/inscription/inscription.jsp").forward(request, response);
		}
	}
	
	private void validationNom(String nom) throws Exception {
		if(nom.isEmpty()) {
			throw new Exception("Merci de saisir un nom.");
		}
		if(!(nom.length()>=1 && nom.length()<=32)) {
			throw new Exception("Le nom doit être entre 1 à 32 caractère(s).");
		}
	}
	
	private void validationPrenom(String prenom) throws Exception {
		if(prenom.isEmpty()) {
			throw new Exception("Merci de saisir un prenom.");
		}
		if(!(prenom.length()>=1 && prenom.length()<=32)) {
			throw new Exception("Le prenom doit être entre 1 à 32 caractère(s).");
		}
	}
	
	private void validationPseudo(String pseudo) throws Exception {
		if(pseudo.isEmpty()) {
			throw new Exception("Merci de saisir un pseudo.");
		}
		if(!(pseudo.length()>=1 && pseudo.length()<=32)) {
			throw new Exception("Le pseudo doit être entre 1 à 32 caractère(s).");
		}
	}
	
	private void validationMdp(String mdp) throws Exception {
		if(mdp.isEmpty()) {
			throw new Exception("Merci de saisir un mot de passe.");
		}
		if(!(mdp.length()>=1 && mdp.length()<=32)) {
			throw new Exception("Le mot de passe doit être entre 6 à 32 caractère(s).");
		}
	}
	
	private void validationConfirmMdp(String mdp, String confirmation) throws Exception {
		if(confirmation.isEmpty()) {
			throw new Exception("Merci de confirmer le mot de passe.");
		}
		if(!(confirmation.length()>=1 && confirmation.length()<=32)) {
			throw new Exception("Le mot de passe doit être entre 6 à 32 caractère(s).");
		}
		if(!mdp.equals(confirmation)) {
			throw new Exception("Les mots de passe entrés sont différents");
		}
	}
	
	private void validationEmail(String email) throws Exception {
		if( !email.isEmpty()) {
			if( !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
				throw new Exception("Merci de saisir une adresse email valide.");
			}
		}
	}
}
