package opendatasoft;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class OpendatasoftRequest {
	
	public static void main(String[] args) throws IOException {
		System.out.println(eventById("48d4f4b6a3e635b38f21793a3b8c3ef2a5f17f68"));
		System.out.println(eventsFromSearch("Mozart").size());
		/*for(Event e: eventsFromSearch("Mozart")) {
			//System.out.println(e);
		}*/
	}
	
	public static List<Event> eventsFromSearch(String searchInfo) throws IOException{
		List<Event> events = new ArrayList<Event>();
		String baseurl = "https://public.opendatasoft.com/api/records/1.0/search/?dataset=evenements-publics-cibul&q="+searchInfo+"&rows=1000&facet=tags&facet=placename&facet=department&facet=region&facet=city&facet=date_start&facet=date_end&facet=pricing_info&facet=updated_at&facet=city_district&refine.tags=concert&timezone=UTC";
		URL url = new URL(baseurl);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		
		while((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		JSONArray json = new JSONObject(response.toString()).getJSONArray("records");
		for(Object entry: json) {
			JSONObject tmp = (JSONObject)entry;
			events.add(convertJsontoEventSearch(tmp));
		}
		
		
		return events;
	}
	
	public static Event eventById(String id) throws IOException {
		String baseurl = "https://public.opendatasoft.com/api/v2/catalog/datasets/evenements-publics-cibul/records/"+id+"?pretty=false&timezone=UTC";
		URL url = new URL(baseurl);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		
		while((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		JSONObject json = new JSONObject(response.toString()).getJSONObject("record");
		return convertJsontoEvent(json);
	}
	
	public static Event convertJsontoEventSearch(JSONObject record) {
		String idevent = record.getString("recordid");
		JSONObject fields = record.getJSONObject("fields");
		String freetext = fields.isNull("free_text")?"":fields.getString("free_text");
		String address = fields.isNull("address")?"":fields.getString("address");
		String city = fields.isNull("city")?"":fields.getString("city");
		String link = fields.isNull("link")?"":fields.getString("link");
		String desc = fields.isNull("description")?"":fields.getString("description");
		String dateend = fields.isNull("date_end")?"":fields.getString("date_end");
		String datestart = fields.isNull("date_start")?"":fields.getString("date_start");
		String title = fields.isNull("title")?"":fields.getString("title");
		String region = fields.isNull("region")?"":fields.getString("region");
		String price = fields.isNull("pricing_info")?"":fields.getString("pricing_info");
		String department = fields.isNull("department")?"":fields.getString("department");
		String image = fields.isNull("image")?"":fields.getString("image");
		return new Event(idevent, freetext, city, title, price, datestart, department, dateend, desc, link, address, region, image);
	}
	
	public static Event convertJsontoEvent(JSONObject record) {
		String idevent = record.getString("id");
		JSONObject fields = record.getJSONObject("fields");
		String freetext = fields.isNull("free_text")?"":fields.getString("free_text");
		String address = fields.isNull("address")?"":fields.getString("address");
		String city = fields.isNull("city")?"":fields.getJSONArray("city").getString(0);
		String link = fields.isNull("link")?"":fields.getString("link");
		String desc = fields.isNull("description")?"":fields.getString("description");
		String dateend = fields.isNull("date_end")?"":fields.getString("date_end");
		String datestart = fields.isNull("date_start")?"":fields.getString("date_start");
		String title = fields.isNull("title")?"":fields.getString("title");
		String region = fields.isNull("region")?"":fields.getJSONArray("region").getString(0);
		String price = fields.isNull("pricing_info")?"":fields.getString("pricing_info");
		String department = fields.isNull("department")?"":fields.getJSONArray("department").getString(0);
		String image = fields.isNull("image")?"":fields.getString("image");
		return new Event(idevent, freetext, city, title, price, datestart, department, dateend, desc, link, address, region, image);
	}
}
