package data;

public class DataHandler {
	private static String data = "";
	private static String oldUrl = "";
	
	
	public static String getData(String url) {
		
		//Gör så att man inte i onödan laddar ner hela skiten om om man redan har samma set från förr.
		if (url.equals(oldUrl)) {
			main.Main.window.textArea.append("*No Download Needed*\n");
			return data;
		}
		oldUrl = url;
		data = WebDownloader.testIt(url);
		return data;
		
		
	}

}
