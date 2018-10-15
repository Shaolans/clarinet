package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
		name = "Inscription",
		urlPatterns = "/inscription")
public class Clarinet_inscription extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_first_name = request.getParameter("user_first_name");
		String user_last_name = request.getParameter("user_last_name");
		String user_pseudo = request.getParameter("user_pseudo");
		String user_pwd = request.getParameter("user_pwd");
		String user_confirm_pwd = request.getParameter("user_confirm_pwd");
		String user_email = request.getParameter("user_email");
		
		String resultat;
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
			validationMdp(user_pwd, user_confirm_pwd);
		} catch (Exception e) {
			erreurs.put("pwd", e.getMessage());
		}
		
		try {
			validationEmail(user_email);
		} catch (Exception e) {
			erreurs.put("email", e.getMessage());
		}
		
		if( erreurs.isEmpty() ) {
			resultat = "Succès";
		}
		else {
			resultat = "Inscription échouée";
		}
		
		// Stockage du résultat et des messages d'erreur dans l'objet request 
		request.setAttribute("erreurs", erreurs);
		request.setAttribute("resultat", resultat);
		
		// Transmission de la paire d'objets request/response à notre JSP
		this.getServletContext().getRequestDispatcher("/inscription/inscription.jsp").forward(request, response);
	}
	
	private void validationNom(String nom) throws Exception {
		if(nom.isEmpty()) {
			throw new Exception("Merci de saisir un nom.");
		}
	}
	
	private void validationPrenom(String prenom) throws Exception {
		if(prenom.isEmpty()) {
			throw new Exception("Merci de saisir un prenom.");
		}
	}
	
	private void validationPseudo(String pseudo) throws Exception {
		if(pseudo.isEmpty()) {
			throw new Exception("Merci de saisir un pseudo.");
		}
	}
	
	private void validationMdp(String mdp, String confirmation) throws Exception {
		if(mdp.isEmpty()) {
			throw new Exception("Merci de saisir un mot de passe.");
		}
		if(confirmation.isEmpty()) {
			throw new Exception("Merci de confirmer le mot de passe.");
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
