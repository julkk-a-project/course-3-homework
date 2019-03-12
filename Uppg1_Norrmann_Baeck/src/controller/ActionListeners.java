package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

import view.Window;


public class ActionListeners {
	
	
	public ActionListeners(Window window) {
	

		//actionListener for "---Do query---"-button (button1)
		window.doQueryButton.addActionListener(new ActionListener(){			

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					window.graph.resetScore();
				}catch(Exception e){
					System.out.println("No clear needed at first run");
				}
						
				window.textArea.append((controller.DataHandler.readWeb((String) window.dataSeries.getSelectedItem(), (String) window.timeSeries.getSelectedItem(), (String) window.symbol.getSelectedItem(), (String) window.timeInterval.getSelectedItem(), (String) window.outputSize.getSelectedItem()))+"\n");
				//window.upperRight.setVisible(true);
				
				
				//handles dataSeries data
				Collections.sort(window.dataSeriesListString);
				window.updateDataSeries();
				window.packMe();
			}
		});
				
				
				
		//actionListener for dataSeries
		window.dataSeries.addActionListener(new ActionListener(){			
					
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println((String) window.dataSeries.getSelectedItem());
				if(model.DataStorer.hasData()) {
							
					try {
						window.graph.resetScore();
					}catch(Exception e){
						System.out.println("No clear needed at first run");
					}
							
					window.textArea.append((controller.DataHandler.readData((String) window.dataSeries.getSelectedItem()))+"\n");			
					window.packMe();
				}else {
					window.textArea.append("**no data to read**\n");
				}
			}		
		});
		
	
			
	}
}
