package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

import model.DataStorer;
import view.Window;


public class ActionListeners {
	
	
	public ActionListeners() {
	

		//actionListener for "---Do query---"-button (button1)
		main.Main.window.doQueryButton.addActionListener(new ActionListener(){			

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				//to Listen for new API Key
				String[] apiKeyArray = { main.Main.window.apiTextArea.getText() };
				main.Main.inis.setKeyValue("API_KEY",apiKeyArray);
				
				try {
					main.Main.window.graph.resetScore();
				}catch(Exception e){
					System.out.println("No clear needed at first run");
				}
						
				main.Main.window.textArea.append((controller.DataHandler.readWeb((String) main.Main.window.dataSeries.getSelectedItem(), 
																				 (String) main.Main.window.timeSeries.getSelectedItem(),
																				 (String) main.Main.window.symbol.getSelectedItem(), 
																				 (String) main.Main.window.timeInterval.getSelectedItem(),
																				 (String) main.Main.window.outputSize.getSelectedItem()))+"\n");
				
				
				//handles dataSeries data
				Collections.sort(main.Main.window.dataSeriesListString);
				main.Main.window.updateDataSeries();
				main.Main.window.packMe();
			}
		});
				
				
				
		//actionListener for dataSeries
		main.Main.window.dataSeries.addActionListener(new ActionListener(){			
					
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//System.out.println((String) main.Main.window.dataSeries.getSelectedItem());
				if(main.Main.dataStorer.hasData()) {
					try {
						main.Main.window.graph.resetScore();
					}catch(Exception e){
						System.out.println("No clear needed at first run");
					}
							
					main.Main.window.textArea.append((controller.DataHandler.readData((String) main.Main.window.dataSeries.getSelectedItem()))+"\n");			
					main.Main.window.packMe();
				}else {
					main.Main.window.textArea.append("**** No data to read ****\n");
				}
			}		
		});
		
	
			
	}
}
