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
	        while(rs.next()) {
	        	res.add(rs.getInt("id_follower"));
	        }
			out.write("<!DOCTYPE html> <html> <head></head> <body><h1>FOLLOW SERVLET</h1></body></html>".getBytes());
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
		try {
			Connection postgre = PostgresqlConnectionProvider.getCon();
			ServletOutputStream out = resp.getOutputStream();
	        String user = ""+req.getSession(false).getAttribute("id_user");
	        String followed = req.getParameter("followed");
	        PreparedStatement pr = postgre.prepareStatement("INSERT INTO FOLLOWERS VALUES (?,?)");
	        pr.setInt(1, Integer.parseInt(user));
	        pr.setInt(2, Integer.parseInt(followed));
	        pr.executeUpdate();
	        pr.close();
	        String outstring = "<!DOCTYPE html> <html> <head></head> <body><h1>"+user+" "+followed+"</h1></body></html>";
	        out.write(outstring.getBytes());
	        out.flush();
	        out.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
    }
	

}
