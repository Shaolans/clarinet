package map_geocalisation;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

public class NominatimConnection {
	private static String BASE_URL = "https://nominatim.openstreetmap.org/search?q=";
	private static String FORMAT = "&format=json";
	
    public static String getLonLat(String address_non_formater) { 
    	String address = address_non_formater.replaceAll(" ", "+");
    	address = address.replaceAll(",", "+");//address format ex: 4+place+jussieu+75005+Paris
    	
        HttpURLConnection con = null;
        InputStream is = null;
        
        try {
            con = (HttpURLConnection) (new URL(BASE_URL + address +FORMAT)).openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.connect();

            StringBuffer buffer = new StringBuffer();
            is = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            while ( (line = br.readLine()) != null){
                buffer.append(line);
            }
            is.close();
            con.disconnect();
            
            JSONArray arr = new JSONArray(buffer.toString());
            JSONObject obj = arr.getJSONObject(0);
            String lonlat = obj.getString("lon")+", "+obj.getString("lat");
            return lonlat;
        }
        catch (Throwable t){
            t.printStackTrace();
        }
        return null;
    }
}
