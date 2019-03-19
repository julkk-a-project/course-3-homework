package model;

import java.util.Map;
import java.util.HashMap;

public class Inis {
	private Map<String, String[]> ini;

	public Inis() {
		ini = new HashMap<String, String[]>();
	}

	public String[] getKeyValue(String key) {
		try {
			return ini.get(key);
		}catch (NullPointerException npe) {
			String[] error  = {"Error, no data found. Check Ini-file."};
			return error;
		}
	}

	public String getKeyValueInt(int i, String key) {
		try {
			String[] temp = ini.get(key);
			return temp[i];
		}catch (NullPointerException npe) {
			return "Error, no data found. Check Ini-file.";
		}
		}	
	
	public void setKeyValue(String key, String[] value) {
		ini.put(key, value);
	}
}