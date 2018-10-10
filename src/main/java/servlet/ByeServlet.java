package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(
        name = "ByeServlet", 
        urlPatterns = {"/bye"}
    )
public class ByeServlet extends HttpServlet {
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ServletOutputStream out = resp.getOutputStream();
        out.write("<!DOCTYPE html> <html> <head></head> <body><h1> BYE BYE clarinet</h1></body></html>".getBytes());
        out.flush();
        out.close();
    }
}