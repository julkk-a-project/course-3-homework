package controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.json.JSONObject;

public class JsonParser {	
	
	//Parsing downloaded data.
	public static String parser(Boolean isSymbol2, String json, String dataSeries) {

		boolean handleDate = DateHandler.needsHandling();
		List<Double> datapoints = new ArrayList<Double>();
		JSONObject obj = new JSONObject(json);
		
		obj.remove("Meta Data");

		Set <String> keys = obj.keySet();
		Iterator<String> it = keys.iterator();
		JSONObject obj2 = obj.getJSONObject(it.next());

		Set <String> keys2 = obj2.keySet();
		Iterator<String> it2 = keys2.iterator();

		//We want to split keys2 at "," and then add to arrayList called arrayOfKeys2
		ArrayList<String> arrayOfKeys2 = new ArrayList<>(keys2);
		
		//READ DATASERIES IF YOU DON'T HAVE ANY YET
		if(main.Main.window.dataSeriesEmpty()) {
			String tempDataSeries = readDataSeries(obj2, it2);
			
			if (dataSeries == null) {
				dataSeries = tempDataSeries;
			}	
		}
		
		if(handleDate == true) {
			List<String> formatedDateList = new LinkedList<String>();
			formatedDateList = arrayOfKeys2;
			
			//parse FDL
			for (int i = 0; i < arrayOfKeys2.size(); i++) {
				formatedDateList.set(i, formatedDateList.get(i).substring(0,10));
			}
				
			for(int i = 0; i < arrayOfKeys2.size();) {
				
				if(!DateHandler.inRange(formatedDateList.get(i))) {
					//kill
					arrayOfKeys2.remove(i);
					//formatedDateList.remove(i);
				
				}else {
					i++;
				}
			}
		}
			
		String log = "===== Showing data for " + dataSeries + " =====\n";
		int i = -1;

		ArrayList<Double> datapointSorter = new ArrayList<Double>();
		ArrayList<String> dateSorter = new ArrayList<String>();

		double max = 0;
		double min = -1;

		while(it2.hasNext() && i < arrayOfKeys2.size()-1) {
			i++;
			JSONObject data = obj2.getJSONObject(it2.next());		
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
			
			for (x = 1; x < dateSorter.size(); x++) { 
				key = dateSorter.get(x);
				key2 = datapointSorter.get(x);
				y = x-1; 

				/* Move elements of arr[0..i-1], that are 
				          greater than key, to one position ahead 
				          of their current position */
				
				while (y >= 0 && dateSorter.get(y).compareTo(key) > 0) { 
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
		try {
			main.Main.window.graph.setFirstX(dateSorter.get(0)); 
		} catch (IndexOutOfBoundsException e) {
			main.Main.window.textArea.append("No first date\n");
		}
		
		try {
			main.Main.window.graph.setLastX(dateSorter.get(dateSorter.size() -1));
		} catch (IndexOutOfBoundsException e) {
			main.Main.window.textArea.append("No last date\n");
		}		

		//Sets max score to graph
		main.Main.window.graph.setMaxScore(max);
		
		//adds Data to pearsonHandler
		main.Main.dataStorer.addPearson(isSymbol2, datapoints);
		
		//Draws datapoints to graph
		main.Main.window.graph.setScore(isSymbol2, datapoints);
		
		//Should return information about what was downloaded, not all content.
		return log;		
	}
	
	
	
	//HelpMetods:
	
	public static String readDataSeries(JSONObject obj2Clone, Iterator<String> it2Clone){ //Return dataseries[0]
		JSONObject data = obj2Clone.getJSONObject(it2Clone.next());	
		
		if(true) { //TODO: ADD ARGuMEnts here LATER?
			int moduloTest = 0;
			String[] protoDataSeries = data.toString().split("\"");
			
			for(int proto = 0; proto < protoDataSeries.length; proto++) {

				//This system purges the unnecessary parts of protoDataSeries, so that we only get dataSeries's's :)
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
		return main.Main.window.getDataSeries(0);
		
	} //method
} //class
