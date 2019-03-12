package model;

public class DataStorer {
	private String data = "";
	private String oldUrl = "";
	
	public boolean hasData() {
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

	public Object getOldUrl() {
		return oldUrl;
	}

	public void setOldUrl(String url) {
		oldUrl = url;
	}
}
