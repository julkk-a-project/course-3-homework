package controller;

import model.JsonParser;

public class DataHandler {
	//private static String data = "";
	//private static String oldUrl = "";
	
	
	public static String getData(String url) {
		
		//So we can look at all dataSeries without downloading more than once.
		if (url.equals(main.Main.dataStorer.getOldUrl()) || url.equals("old")) {
			main.Main.window.textArea.append("**** No Download Needed ****\n");
			return main.Main.dataStorer.getData();
		} else {
			main.Main.dataStorer.setOldUrl(url);
			main.Main.window.textArea.append("**** Downloading ****\n");
			
			main.Main.dataStorer.setData(WebDownloader.testIt(url));
			main.Main.window.textArea.append("**** Downloaded ****\n");
			return main.Main.dataStorer.getData();
		}	
	}
	
	//to controller package
	public static String readWeb(String dataSeries, String timeSeries, String symbol, String timeInterval, String outputSize) {
				
		//takes in information from webSite
		String json = controller.DataHandler.getData("https://www.alphavantage.co/query?function="+timeSeries+"&symbol="+symbol+"&interval="+timeInterval+"&outputsize="+outputSize+"&apikey="+main.Main.inis.getKeyValueInt(0, "API_KEY"));
			
		try {			
			return JsonParser.parser(json, dataSeries);
			} catch (Exception e) {
				if(dataSeries == null) {
					return "**** Now select a dataSeries ****";
				} else {
					return "**** No \""+ dataSeries + "\" found ****";			
				}
			}
	}
		
		
	//2 controller package. reading data from dataSeries
	public static String readData(String dataSeries) {
		try {
			String json = controller.DataHandler.getData("old");
			return JsonParser.parser(json, dataSeries);
		}catch(Exception e) {
			if(dataSeries == null) {
				return "**** Please select a dataSeries again ****";
			}else {
				return "**** No \""+ dataSeries + "\" found ****";						
			}
		}
	}
}