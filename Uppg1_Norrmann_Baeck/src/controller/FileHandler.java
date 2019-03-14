package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class FileHandler {
	public static void readFile(String path) {
		try {
			FileReader reader = new FileReader(path);
			BufferedReader buf = new BufferedReader(reader);
			
			String line;
			while ((line = buf.readLine()) != null) {
				String[] temp = line.split("=");
				String key = temp[0];
				String[] value = temp[1].split(",");
				
				for(int i = 0; i < value.length; i++) {
					value[i] = value[i].trim();
				}
				main.Main.inis.setKeyValue(key, value);
			}
			
			buf.close();
			
		} catch (IOException ioe) {
			
		}
	}

}
