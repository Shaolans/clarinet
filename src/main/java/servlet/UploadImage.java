package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.JSONObject;

import bd.UserTools;

@WebServlet(
		name = "Upload",
		urlPatterns = "/upload")

@MultipartConfig
public class UploadImage extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

		JSONObject res = new JSONObject();
		if(req.getSession(false)!=null){
			
				    
		    Part p = req.getPart("photo");
			
			
			InputStream reader = p.getInputStream();
		    
			
		    byte[] imageBytes = new byte[Long.BYTES];
		    
		    reader.read(imageBytes);

			int id_user = (Integer)req.getSession(false).getAttribute("id_user");
			System.out.println(id_user);
			UserTools.uploadImage(id_user, imageBytes);
			
			res.put("rep", "Image recue");
		}
		else
			res.put("err", "Upload impossible car session inexistante");
		
		resp.setContentType("application/json");
		resp.getWriter().println(res);
	}

}