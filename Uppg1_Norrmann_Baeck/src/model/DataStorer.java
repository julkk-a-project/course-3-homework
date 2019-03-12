package model;

public class DataStorer {
	private static String data = "";
	private String url = "";
	
	public static boolean hasData() {
		if (!data.equals("")) {
			return true;
		} else {
			return false;
		}
	}
	
	public String getData() {
		return data;
		
	}
	
	public void setData(String data) {
		this.data = data;
		
	}
}
