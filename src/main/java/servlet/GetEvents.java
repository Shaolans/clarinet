package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import opendatasoft.Event;
import opendatasoft.OpendatasoftRequest;

@WebServlet(
		name = "GetEvents", 
        urlPatterns = {"/get/events"}
)
public class GetEvents extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
		String query = req.getParameter("query");
		String nbrows = req.getParameter("nbrows");
		String startrow = req.getParameter("startrow");
		List<Event> events = OpendatasoftRequest.eventsFromSearch(query, Integer.parseInt(nbrows), Integer.parseInt(startrow));
		JSONObject res = new JSONObject();
		
		res.put("query", query).put("nbrows", nbrows).put("startrow", startrow);
		JSONArray eventsarray = new JSONArray();
		res.put("events", eventsarray);
		JSONObject tmp;
		for(Event e: events) {
			tmp = new JSONObject();
			tmp.put("id", e.getId());
			tmp.put("title", e.getTitle());
			tmp.put("address", e.getAddress());
			tmp.put("start_date", e.getDatestart());
			tmp.put("tags", e.getTags());
			eventsarray.put(tmp);
		}
		resp.setContentType("application/json");
		resp.getWriter().write(res.toString());
	}
}
