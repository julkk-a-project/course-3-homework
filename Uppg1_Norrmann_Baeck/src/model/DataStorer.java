package model;

import java.util.List;

public class DataStorer {
	private String data1 = "";
	private String data2 = "";
	private String oldUrl1 = "";
	private String oldUrl2 = "";
	private List<Double> symbol1Datapoints;
	private List<Double> symbol2Datapoints;
	
	
	
	//checks if symbol2 has data
	public boolean hasData(Boolean isSymbol2) {
		String tempData = "";
		if(isSymbol2) {
			tempData = data2;
		}else {
			tempData = data1;
		}
		if (!tempData.equals("")) {
			return true;
		} else {
			return false;
		}
	}
	
	
	
	//gets Data
	public String getData(Boolean isSymbol2) {
		if(isSymbol2) {
			return data2;
		} else {
			return data1;
		}
	}
	
	
	
	//sets data
	public void setData(Boolean isSymbol2, String data) {
		if(isSymbol2) {
			this.data2 = data;			
		} else {
			this.data1 = data;
		}
	}

	
	
	//gets old url
	public Object getOldUrl(Boolean isSymbol2) {
		if (isSymbol2) {
			return oldUrl2;
		} else {
			return oldUrl1;	
		}	
	}

	
	
	//sets old url
	public void setOldUrl(Boolean isSymbol2, String url) {
		if(isSymbol2) {
			oldUrl2 = url;
		}else {
			oldUrl1 = url;
		}
	}
	
	
	
	//gets datapoints for symbol1
	public List<Double> getSymbol1Datapoints() {
		return symbol1Datapoints;
	}
	
	
	
	//gets datapoints for symbol2
	public List<Double> getSymbol2Datapoints() {
		return symbol2Datapoints;
	}
	
	
	
	//adds Pearson
	public void addPearson(boolean isSymbol2, List<Double> list) {
		if(isSymbol2) {
			symbol2Datapoints = list;
		} else {
			symbol1Datapoints = list;
		}
	}
	
	
	
	//resets datapoints, (not used, I think, but saved if we ever would need it)
	public void reset() {
		symbol1Datapoints.clear();
		symbol2Datapoints.clear();
	}

}
