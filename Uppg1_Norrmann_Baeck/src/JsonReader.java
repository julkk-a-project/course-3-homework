import java.util.Iterator;
import java.util.Set;

import org.json.JSONObject;

public class JsonReader {
	public static String readWeb(String dataSeries, String timeSeries, String symbol, String timeInterval, String outputSize) {
		
		
		//takes in information from website
		String json = WebDownloader.testIt("https://www.alphavantage.co/query?function="+timeSeries+"&symbol="+symbol+"&interval="+timeInterval+"&outputsize="+outputSize+"&apikey=X0E92VRLD6Z3KLH0");
		
		
		//Parse text here:
		String log = json; //temporary
		
		JSONObject obj = new JSONObject(json);
		Set <String> keys = obj.getJSONObject("Time Series (" + timeInterval + ")").keySet();
		
		Iterator<String> it = keys.iterator();
		while(it.hasNext()) {
			System.out.println(obj.getJSONObject(it.next()).getString(dataSeries));
			//String pageName = obj.getJSONObject(it.next()).getString(dataSeries);	
		}
		
		//for(String s: keys)
			//System.out.println(s);
		//obj.get(keys);
		//for (String s: keys)
		//	String pageName = obj.getJSONObject("Time Series ("+timeSeries+")").getString(keys);
		
		
		
		
		//Should return information about what was downloaded, not all content.
		return log;
		
	}
}
