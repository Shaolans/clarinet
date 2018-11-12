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

import database.PostgresqlConnectionProvider;
import database.utils.UserTools;
@WebServlet(
		name = "Unfollow", 
        urlPatterns = {"/unfollow"}
)
public class Unfollow extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
			
		JSONObject res = new JSONObject();
	        Integer id_user = (Integer)req.getSession(false).getAttribute("id_user");
	        Integer followed = Integer.valueOf(req.getParameter("id_ami"));
	        if(UserTools.unfollow(id_user, followed)){
	        	res.put("id_ami", followed);
	        }
	        else
	        	res.put("err","erreur de la base de donn�es");
	        
	        resp.setContentType("application/json");
			resp.getWriter().println(res);

    }
	

}
