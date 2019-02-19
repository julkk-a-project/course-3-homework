import org.json.JSONObject;

public class JsonReader {
	public static String readWeb(String dataSeries, String timeSeries, String symbol, String timeInterval, String outputSize) {
		
		
		//takes in information from website
		String json = WebDownloader.testIt();
		
		
		//Parse text here:
		String log = json; //temporary
		
		JSONObject obj = new JSONObject(WebDownloader.testIt());
		
		
		
		
		
		//Should return information about what was downloaded, not all content.
		return log;
		
	}
}
