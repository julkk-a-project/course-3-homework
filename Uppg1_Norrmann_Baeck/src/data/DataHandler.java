package data;

public class DataHandler {
	private static String data = "";
	private static String oldUrl = "";
	
	
	public static String getData(String url) {
		
		//G�r s� att man inte i on�dan laddar ner hela skiten om om man redan har samma set fr�n f�rr.
		if (url.equals(oldUrl)) {
			main.Main.window.textArea.append("*No Download Needed*\n");
			return data;
		}
		oldUrl = url;
		data = WebDownloader.testIt(url);
		return data;
		
		
	}

}
