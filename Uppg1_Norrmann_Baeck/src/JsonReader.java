

public class JsonReader {
	public static String readWeb() {
		
		
		//takes in information from website
		String json = WebDownloader.testIt();
		
		
		//Parse text here:
		String log = json; //temporary
		
		
		
		
		//Should return information about what was downloaded, not all content.
		return log;
		
	}
}
