
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.json.JSONObject;

public class JsonReader {
	private static List<Double> datapoints = new ArrayList<Double>();
	

	public static String readWeb(String dataSeries, String timeSeries, String symbol, String timeInterval, String outputSize) {
		
		
		//takes in information from website
		String json = WebDownloader.testIt("https://www.alphavantage.co/query?function="+timeSeries+"&symbol="+symbol+"&interval="+timeInterval+"&outputsize="+outputSize+"&apikey=X0E92VRLD6Z3KLH0");
		
		
		//Parse text here:

		
		
		JSONObject obj = new JSONObject(json);
		obj.remove("Meta Data");
		
		
		//Set<String> keys1 = obj.getJSONObject(); 
		
		Set <String> keys = obj.keySet();
		
		Iterator<String> it = keys.iterator();
		JSONObject obj2 = obj.getJSONObject(it.next());
		
		Set <String> keys2 = obj2.keySet();
		Iterator<String> it2 = keys2.iterator();
		System.out.println(keys2);
		//i want to split keys2 at "," and then add to arrayList called arrayOfKeys2
		ArrayList<String> arrayOfKeys2 = new ArrayList<>(keys2); 
				//it2.toString().split(",");
		Collections.sort(arrayOfKeys2);
		System.out.println(arrayOfKeys2.size());
		/*for (int i = 0; i < arrayOfKeys2.size(); i++) {
			//String[] string = ;
			//arrayOfKeys2[i] = string;
			System.out.println(arrayOfKeys2.get(i));
		}*/
		
		
		String log = "===== Showing data for " + dataSeries + " =====\n";
		int i = -1;
		while(it2.hasNext()) {
			i++;
			JSONObject data = obj2.getJSONObject(it2.next());
			log += "Date: " + arrayOfKeys2.get(i) + ": "+data.getString(dataSeries) + "\n";
			//insert graph functionality here
			//blabla = data.getString(dataSeries);
			Double datapoint = Double.parseDouble(data.getString(dataSeries));
			datapoints.add(datapoint);
		}
			
		Window.graph.setScore(datapoints);
		//Should return information about what was downloaded, not all content.
		return log;
		
	}
}
