package map_geolocalisation;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONArray;
import org.json.JSONObject;

public class NominatimConnection {
	private static String BASE_URL = "https://nominatim.openstreetmap.org/search?q=";
	private static String FORMAT = "&format=json";
	
    public static String getLonLat(String address_non_formater) { 
    	String add = address_non_formater.replaceAll(" ", "+");
    	add = add.replaceAll(",", "+");//address format ex: 4+place+jussieu+75005+Paris
    	add = new String(add.getBytes( Charset.forName("ISO-8859-1")),Charset.forName("UTF-8"));
    	
    	byte[] byteText = add.getBytes(Charset.forName("UTF-8"));
    	String address = "";
		try {
			address = new String(byteText , "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
    	
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