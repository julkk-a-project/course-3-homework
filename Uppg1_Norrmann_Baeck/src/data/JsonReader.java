package data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.JSONObject;

public class JsonReader {
		
	private static List<Double> datapoints = new ArrayList<Double>();

	
	public static String readWeb(String dataSeries, String timeSeries, String symbol, String timeInterval, String outputSize) {
			
		//takes in information from webSite
		String json = data.DataHandler.getData("https://www.alphavantage.co/query?function="+timeSeries+"&symbol="+symbol+"&interval="+timeInterval+"&outputsize="+outputSize+"&apikey=X0E92VRLD6Z3KLH0");
		
		try {
			return parser(json, dataSeries);
		} catch (Exception e) {
			if(dataSeries == null) {
				return "***Now select a dataSeries***";
			} else {
				return "***No \""+ dataSeries + "\" found***";			
			}
		}
	}
	
	
	//reading data from dataSeries
	public static String readData(String dataSeries) {
		try {
			String json = data.DataHandler.getData("old");
			return parser(json, dataSeries);
		}catch(Exception e) {
			if(dataSeries == null) {
				return "***Please select a dataSeries again***";
			}else {
				return "***No \""+ dataSeries + "\" found***";				
			}
		}
	}
	
	//Parsing downloaded data
	public static String parser(String json, String dataSeries) {
		
		JSONObject obj = new JSONObject(json);
		obj.remove("Meta Data");
		
		//Set<String> keys1 = obj.getJSONObject(); 
		
		Set <String> keys = obj.keySet();
		Iterator<String> it = keys.iterator();
		JSONObject obj2 = obj.getJSONObject(it.next());
		
		Set <String> keys2 = obj2.keySet();
		Iterator<String> it2 = keys2.iterator();
		
		//i want to split keys2 at "," and then add to arrayList called arrayOfKeys2
		ArrayList<String> arrayOfKeys2 = new ArrayList<>(keys2); 
		//Collections.sort(arrayOfKeys2);
		/*for (int i = 0; i < arrayOfKeys2.size(); i++) {
			//String[] string = ;
			//arrayOfKeys2[i] = string;
			System.out.println(arrayOfKeys2.get(i));
		}*/
		
		double max = 0;
		double min = -1;
		
		String log = "===== Showing data for " + dataSeries + " =====\n";
		int i = -1;

		ArrayList<Double> datapointSorter = new ArrayList<Double>();
		ArrayList<String> dateSorter = new ArrayList<String>();
		

		
		
		
		while(it2.hasNext()) {
			i++;
			JSONObject data = obj2.getJSONObject(it2.next());	
			
			//getDataSeries's's
			
			if(i == 0 && main.Main.window.DataSeriesEmpty()) {
				int moduloTest = 0;
				String[] protoDataSeries = data.toString().split("\"");
				//String[] DataSeriesArray;
				
				for(int proto = 0; proto < protoDataSeries.length; proto++) {
					
					//This system purges the unneccesary parts of protoDataSeries, so that we only get dataSeries's's :)
								//we studied the patterns, and the split gives the following pattern of usable and unusable data:
									// un == unusable, us == usable
									//un, us, un, un, un, us, un, un, un, us... ... us, un, un, un.
					
									//PS. i think we could have just used keySet() instead of this fancy if-tree :)))
					
					if(proto != 0) {
						if (proto != protoDataSeries.length-1 || proto != protoDataSeries.length-2 || proto != protoDataSeries.length-3) {
							
							if(moduloTest % 4 == 0) {
								//System.out.println("Succ"+protoDataSeries[proto]);
								main.Main.window.setDataSeries(protoDataSeries[proto]);
							}
							moduloTest++;
						}
					}
				}	
			}
			
			
			
			Double datapoint = Double.parseDouble(data.getString(dataSeries));
			if(datapoint > max) {
				max = datapoint;
			}
			if(datapoint < min || min == -1) {
				min = datapoint;
			}
			
			//SORTS EVERYTHING ACCODRING TO DATE
			datapointSorter.add(datapoint);
			dateSorter.add(arrayOfKeys2.get(i));
			int x, y; 
			String key;
			double key2;
			   for (x = 1; x < dateSorter.size(); x++) 
			   { 
			       key = dateSorter.get(x);
			       key2 = datapointSorter.get(x);
			       
			       y = x-1; 
			  
			       /* Move elements of arr[0..i-1], that are 
			          greater than key, to one position ahead 
			          of their current position */
			       while (y >= 0 && dateSorter.get(y).compareTo(key) > 0) 
			       { 
			    	   dateSorter.set(y+1, dateSorter.get(y));
			    	   datapointSorter.set(y+1, datapointSorter.get(y));
			           y = y-1; 
			       }
			       dateSorter.set(y+1, key);
			       datapointSorter.set(y+1, key2);
			   } 	
		}
		
		
		for (int j = 0; j < dateSorter.size(); j++) {
			datapoints.add(datapointSorter.get(j));
			log += "Date: " + dateSorter.get(j) + ": "+datapointSorter.get(j) + "\n";	
		}
		
		//Get first and last date from ArrayList dateSorter
		main.Main.window.graph.setFirstX(dateSorter.get(0)); 
		main.Main.window.graph.setLastX(dateSorter.get(dateSorter.size() -1));

		//Sets max score to graph
		main.Main.window.graph.setMaxScore(max);
		
		//Draws datapoints to graph
		main.Main.window.graph.setScore(datapoints);
		//Window.upperRight.revalidate();
		//Should return information about what was downloaded, not all content.
		return log;
		
		
	}
}
