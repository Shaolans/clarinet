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
		name = "bio",
		urlPatterns = "/bio")
public class Bio extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
		

		JSONObject res = new JSONObject();
		if(req.getSession(false)!=null){
		
			String msg = req.getParameter("msg");
			int id_user = (Integer)req.getSession(false).getAttribute("id_user");
			UserTools.changerBio(id_user,msg);
			
		
			System.out.println(msg);
			res.put("rep", msg);
		
		}
		else
			res.put("err", "Impossible de changer la bio car session inexistante");
		
		resp.setContentType("application/json");
		resp.getWriter().println(res);
		
	
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
		

		JSONObject res = new JSONObject();
		if(req.getSession(false)!=null){
		
			
			int id_user = (Integer)req.getSession(false).getAttribute("id_user");
			String msg = UserTools.getBio(id_user);

			res.put("rep", msg);
		
		}
		else
			res.put("err", "Impossible de changer la bio car session inexistante");
		
		resp.setContentType("application/json");
		resp.getWriter().println(res);
		
	
	}
	
}
