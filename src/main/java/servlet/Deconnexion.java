package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet(
		name = "Deconnexion",
		urlPatterns = "/deconnexion")
public class Deconnexion extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

		if(req.getSession(false)!=null){
			req.setAttribute("id_user", null);
			req.changeSessionId();
			req.getSession(false).invalidate();
			
			
		}

		resp.sendRedirect("/connexion/connexion.jsp");
	}
	
}
