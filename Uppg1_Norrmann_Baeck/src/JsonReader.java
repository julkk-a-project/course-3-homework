import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.json.JSONObject;

public class JsonReader {
	public static String readWeb(String dataSeries, String timeSeries, String symbol, String timeInterval, String outputSize) {
		
		
		//takes in information from website
		String json = WebDownloader.testIt("https://www.alphavantage.co/query?function="+timeSeries+"&symbol="+symbol+"&interval="+timeInterval+"&outputsize="+outputSize+"&apikey=X0E92VRLD6Z3KLH0");
		
		
		//Parse text here:

		
		
		JSONObject obj = new JSONObject(json);
		obj.remove("metadata");
		
		
		//Set<String> keys1 = obj.getJSONObject(); 
		
		Set <String> keys = obj.keySet();
		
		Iterator<String> it = keys.iterator();
		JSONObject obj2 = obj.getJSONObject(it.next());
		
		Set <String> keys2 = obj2.keySet();
		Iterator<String> it2 = keys2.iterator();
		System.out.println(keys2);
		ArrayList<String> arrayOfKeys2 = new ArrayList<>();
		//i want to split keys2 at "," and then add to arrayList called arrayOfKeys2
		
		String log = "===== Showing data for " + dataSeries + " =====\n";
		int i = -1;
		while(it2.hasNext()) {
			i++;
			JSONObject data = obj2.getJSONObject(it2.next());
			log += "Date: " + arrayOfKeys2.get(i) + ": "+data.getString(dataSeries) + "\n";
			//insert graph functionality here
			//blabla = data.getString(dataSeries);
		}
			
		
		//Should return information about what was downloaded, not all content.
		return log;
		
	}
}
