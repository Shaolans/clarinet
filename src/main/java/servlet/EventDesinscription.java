package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import database.utils.UserTools;

@WebServlet(
		name = "Desinscription evenement", 
        urlPatterns = {"/event/noParticipate"}
)
public class EventDesinscription extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
		Integer id_user = (Integer)req.getSession(false).getAttribute("id_user");
		String id_event = req.getParameter("id_event");
		JSONObject res = new JSONObject();
		
		if(UserTools.supprimerEventParticipation(id_user,id_event)){
			res.put("rep", "ok");
		}
		else
			res.put("err", "erreur");
		
		resp.setContentType("application/json");
		resp.getWriter().write(res.toString());

	}

}
