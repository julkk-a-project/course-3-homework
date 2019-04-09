package controller;

public class DataHandler {


	//private static String data = "";
	//private static String oldUrl = "";
	
	
	public static String getData(Boolean isSymbol2, String url) {
		
		//So we can look at all dataSeries without downloading more than once.
		if (url.equals(main.Main.dataStorer.getOldUrl(isSymbol2)) || url.equals("old")) {
			main.Main.window.textArea.append("**** No Download Needed ****\n");
			return main.Main.dataStorer.getData(isSymbol2);
		} else {
			main.Main.dataStorer.setOldUrl(isSymbol2, url);
			main.Main.window.textArea.append("**** Downloading ****\n");
			
			main.Main.dataStorer.setData(isSymbol2, WebDownloader.testIt(url));
			main.Main.window.textArea.append("**** Downloaded ****\n");
			return main.Main.dataStorer.getData(isSymbol2);
		}	
	}
	
	//to controller package
	public static String readWeb(Boolean isSymbol2, String dataSeries, String timeSeries, String symbol, String timeInterval, String outputSize) {
				
		//takes in information from webSite
		String json = controller.DataHandler.getData(isSymbol2, "https://www.alphavantage.co/query?function="+timeSeries
														+"&symbol="+symbol+"&interval="+timeInterval
														+"&outputsize="+outputSize+"&apikey="
														+main.Main.inis.getKeyValueInt(0, "API_KEY"));
		
		
		try {			
			return JsonParser.parser(isSymbol2, json, dataSeries);
		} catch (Exception e) {
			if(!main.Main.window.dataSeriesEmpty()) {
				return "**** Now you can select a dataSeries ****";
			} else if (dataSeries != null) {
				return "**** No \""+ dataSeries + "\" found ****";			
			} else {
				return "**** No dataSeries found ****";
			}
		}
	}
		
		
	//2 controller package. reading data from dataSeries
	public static String readData(Boolean isSymbol2, String dataSeries) {
		//System.out.println("(In DataHandler) dataSeries: " + dataSeries);
		try {
			String json = controller.DataHandler.getData(isSymbol2, "old");
			return JsonParser.parser(isSymbol2, json, dataSeries);
		}catch(Exception e) {

			//System.out.println("(In DataHandler) " + e);
			if(dataSeries == null) {
				return "**** Please select a dataSeries again ****";
			}else {
				return "**** No \""+ dataSeries + "\" found ****";						
			}
		}
	}
}