package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import bd.UserTools;
import database.PostgresqlConnectionProvider;
@WebServlet(
		name = "Follow", 
        urlPatterns = {"/follow"}
)
public class Follow extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
		
		try {
			ServletOutputStream out = resp.getOutputStream();
			Connection postgre = PostgresqlConnectionProvider.getCon();
			String user = ""+req.getSession(false).getAttribute("id_user");
	        PreparedStatement pr = postgre.prepareStatement("SELECT id_follower FROM FOLLOWERS WHERE id_user = ?");
	        pr.setInt(1, Integer.parseInt(user));
	        ResultSet rs = pr.executeQuery();
	        List<Integer> res = new ArrayList<Integer>();
	        out.print("<!DOCTYPE html> <html> <head></head> <body><h1>FOLLOW SERVLET</h1>");
	        while(rs.next()) {
	        	res.add(rs.getInt("id_follower"));
	        	out.println(rs.getInt("id_follower"));
	        }
	        out.print("</body></html>");
	        
			//out.write("<!DOCTYPE html> <html> <head></head> <body><h1>FOLLOW SERVLET</h1></body></html>".getBytes());
			out.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
		
			JSONObject res = new JSONObject();
			Integer id_user = (Integer)req.getSession(false).getAttribute("id_user");
			Integer id_ami = Integer.parseInt(req.getParameter("id_ami"));
			
			if(UserTools.follow(id_user, id_ami)){
	        	res.put("id_ami", id_ami);
	        }
	        else
	        	res.put("err","erreur de la base de données");
	        
	        resp.setContentType("application/json");
			resp.getWriter().println(res);
			
		
    }
	

}
