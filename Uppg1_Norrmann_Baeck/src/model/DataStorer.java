package model;

public class DataStorer {
	private String data1 = "";
	private String data2 = "";
	private String oldUrl1 = "";
	private String oldUrl2 = "";
	
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
	
	public String getData(Boolean isSymbol2) {
		if(isSymbol2) {
			return data2;
		} else {
			return data1;
		}
	}
	
	public void setData(Boolean isSymbol2, String data) {
		if(isSymbol2) {
			this.data2 = data;			
		} else {
			this.data1 = data;
		}
		
	}

	public Object getOldUrl(Boolean isSymbol2) {
		if (isSymbol2) {
			return oldUrl2;
		} else {
			return oldUrl1;	
		}
		
	}

	public void setOldUrl(Boolean isSymbol2, String url) {
		if(isSymbol2) {
			oldUrl2 = url;
		}else {
			oldUrl1 = url;
		}
	}
}
