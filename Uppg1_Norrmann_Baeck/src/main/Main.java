package main;

import controller.ActionListeners;
import model.DataStorer;
import view.Window;

public class Main {
	public static Window window;
	public static DataStorer dataStorer;
	public static ActionListeners actionListeners;
	
	public static void main(String[] args) {
		window = new Window();
		dataStorer = new DataStorer();
		actionListeners = new ActionListeners();
	}
}