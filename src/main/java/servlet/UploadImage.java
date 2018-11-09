package servlet;

import java.awt.Component;
import java.awt.Frame;
import java.awt.Window;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.JSONObject;
import org.postgresql.util.ReaderInputStream;

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
		    
			String type = p.getContentType().split("/")[1];
			
			InputStream reader = p.getInputStream();
			

		    BufferedImage bImageFromConvert = ImageIO.read(reader); 
		    ByteArrayOutputStream baos = new ByteArrayOutputStream();
		    ImageIO.write( bImageFromConvert, type, baos );
		    baos.flush();
		    byte[] imageBytes = baos.toByteArray();
		    baos.close();
		    
			int id_user = (Integer)req.getSession(false).getAttribute("id_user");
			
			UserTools.uploadImage(id_user, imageBytes,  p.getContentType());
			
			res.put("rep", "Image recue");
		}
		else
			res.put("err", "Upload impossible car session inexistante");
		
		resp.setContentType("application/json");
		resp.getWriter().println(res);
	}

}
