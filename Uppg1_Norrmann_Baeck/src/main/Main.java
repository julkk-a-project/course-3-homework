package main;

import controller.ActionListeners;
import controller.FileHandler;
import model.DataStorer;
import model.Inis;
import view.Window;

public class Main {
	public static Window window;
	public static DataStorer dataStorer;
	public static ActionListeners actionListeners;
	public static Inis inis;
	public static FileHandler fileHandler;
	
	
	public static void main(String[] args) {
		inis = new Inis();
		FileHandler.readFile("StockAnalyzer.ini");
		window = new Window();
		dataStorer = new DataStorer();
		actionListeners = new ActionListeners();
	}
}