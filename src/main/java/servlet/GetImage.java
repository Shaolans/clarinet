package servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.utils.UserTools;


@WebServlet(
		name = "Get Image",
		urlPatterns = "/getImage")
public class GetImage extends HttpServlet {
	 private static final long serialVersionUID = 6290659385134794998L;
	 
     public void doGet(HttpServletRequest request, 
             HttpServletResponse response) throws ServletException, IOException{
    	 
    	 int id_user = (Integer)request.getSession(false).getAttribute("id_user");
             
    	 try{ 
    	 List<Object> res =  UserTools.getImageAndType(id_user);
             if(res.size()==2){
            	 
             
                byte [] rb =  (byte[])res.get(0);
                String type = (String)res.get(1);
                
              if(rb!=null)
            	  System.out.println(type);
                 
                response.reset();
                response.setContentType(type);
                response.getOutputStream().write(rb,0,rb.length);
                response.getOutputStream().flush();   
                  
            }
    	 }
            catch (Exception e){
              e.printStackTrace();
            }
          }
}
