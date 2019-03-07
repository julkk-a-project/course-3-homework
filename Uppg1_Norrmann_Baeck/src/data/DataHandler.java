package data;

public class DataHandler {
	private static String data = "";
	private static String oldUrl = "";
	
	
	public static String getData(String url) {
		
		//So we can look at all dataSeries without downloading more than once.
		if (url.equals(oldUrl) || url.equals("old")) {
			main.Main.window.textArea.append("*No Download Needed*\n");
			return data;
		} else {
			oldUrl = url;
			main.Main.window.textArea.append("****Downloading****\n");
			
			data = WebDownloader.testIt(url);
			main.Main.window.textArea.append("****Downloaded****\n");
			return data;
		}	
	}
}
