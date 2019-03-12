package main;

import controller.ActionListeners;
import view.Window;

public class Main {
	public static Window window;
	public static ActionListeners actionListeners;
	
	public static void main(String[] args) {
		window = new Window();
		actionListeners = new ActionListeners(window);
		
	}
}