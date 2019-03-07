package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

public class Window extends JFrame {
	
	 
	private static final long serialVersionUID = 1L;
	
	
	private final JSplitPane splitPane;  	// split the window in right and left
	private final JSplitPane leftSplitPane; // splits textArea from buttons
    private final JPanel upperRight;      	// container panel for graph
    private final JPanel upperLeft;    		// container panel for buttons
    private final JPanel downLeft;			// container panel for textArea
    public Graph graph;						// our graph
    public JTextArea textArea;     			// the textArea
    
    private List<String> dataSeriesListString;	//ListArray used as temp storage for dataSeries
    private JComboBox<String> dataSeries;		//dataSeries alternatives
    
    boolean hasData = false;
	
    //constructor
	public Window(){
		
		
		splitPane = new JSplitPane();
		leftSplitPane = new JSplitPane();
		
		
		upperLeft = new JPanel(new GridBagLayout());
		
		
		upperRight = new JPanel(new GridBagLayout());
		
		
		/*downRight = new JPanel(new GridBagLayout());
		splitPane.add(downRight);*/
		
		
		downLeft = new JPanel();
		
		
		this.setLayout(new GridLayout());
		this.add(splitPane);
		

		leftSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);  // we want it to split the window horizontally
		leftSplitPane.setDividerLocation(300);                    // the initial position of the divider is 400 (our window is 800 pixels high)
		leftSplitPane.setTopComponent(upperLeft);                 // at the top we want our "topPanel"
		leftSplitPane.setBottomComponent(downLeft);
		
		
		splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);  // we want it to split the window horizontally
        splitPane.setDividerLocation(400);                    	// the initial position of the divider is 400 (our window is 800 pixels high)
        splitPane.setTopComponent(leftSplitPane);               // at the top we want our "topPanel"
        splitPane.setBottomComponent(upperRight);
		
		this.setTitle("My graph");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1920, 1080); //1080p
		this.setResizable(true);
		
		this.setLocationRelativeTo(null);
		
		JButton button1 = new JButton("--- Do query ---");		//new button
		
		
		//5 dropdownboxes dataSeriesString, timeSeriesString, symbolString, timeIntervalString and outpusizeString
		
		
		
		//initializes dataSeries list:
	   
		dataSeriesListString = new ArrayList<String>();
		
		//String[] dataSeriesString = {"1. open", "2. high", "3. low", "4. close", "5. volume", null, null, null};
		
		dataSeries = new JComboBox<String>();	

		
		
		
		//"static" comboboxes:
		
		String[] timeSeriesString = {"TIME_SERIES_INTRADAY", "TIME_SERIES_DAILY", "TIME_SERIES_DAILY_ADJUSTED", "TIME_SERIES_WEEKLY", 
				"TIME_SERIES_WEEKLY_ADJUSTED", "TIME_SERIES_MONTHLY", "TIME_SERIES_MONTHLY_ADJUSTED"};
		JComboBox<String> timeSeries = new JComboBox<String>(timeSeriesString);

		String[] symbolString = {"AAPL", "GOOG", "INTC", "KO", "MSFT", "WMT"};
		JComboBox<String> symbol = new JComboBox<String>(symbolString);

		String[] timeIntervalString = {"1min", "5min", "15min", "30min", "60min"};
		JComboBox<String> timeInterval = new JComboBox<String>(timeIntervalString);

		String[] outputSizeString = {"compact", "full"};
		JComboBox<String> outputSize = new JComboBox<String>(outputSizeString);
		
		
		

		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.weightx = 0.5;
		//c.fill = GridBagConstraints.NORTH;
		
		
		//positions for the labels of dropDownBoxes
		c.gridx = 0;
		c.gridy = 0;
		upperLeft.add(new Label("Data Series"), c);
		

		c.gridx = 0;
		c.gridy = 1;
		upperLeft.add(new Label("Time Series"), c);
		

		c.gridx = 0;
		c.gridy = 2;
		upperLeft.add(new Label("Symbol"), c);


		c.gridx = 0;
		c.gridy = 3;
		upperLeft.add(new Label("Time Interval"), c);
		

		c.gridx = 0;
		c.gridy = 4;
		upperLeft.add(new Label("Output Size"), c);



		//position for our dropDownBoxes
		c.gridx = 1;
		c.gridy = 0;
		upperLeft.add(dataSeries, c);
		
		c.gridx = 1;
		c.gridy = 1;
		upperLeft.add(timeSeries, c);
		
		c.gridx = 1;
		c.gridy = 2;
		upperLeft.add(symbol, c);
		
		c.gridx = 1;
		c.gridy = 3;
		upperLeft.add(timeInterval, c);
		
		c.gridx = 1;
		c.gridy = 4;
		upperLeft.add(outputSize, c);

		c.gridx = 1;
		c.gridy = 5;
		upperLeft.add(button1, c);
		
		
		
		//textArea
		textArea = new JTextArea (25,33);
		JScrollPane scroll = new JScrollPane(textArea);
		textArea.setEditable(false);
		
		//position for textArea
		downLeft.add(scroll,c);
		
		
		
		
		//actionListener for "---Do query---"-button (button1)
		button1.addActionListener(new ActionListener(){			

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					graph.resetScore();
				}catch(Exception e){
					System.out.println("No clear needed at first run");
				}
				textArea.append((data.JsonReader.readWeb((String) dataSeries.getSelectedItem(), (String) timeSeries.getSelectedItem(), (String) symbol.getSelectedItem(), (String) timeInterval.getSelectedItem(), (String) outputSize.getSelectedItem()))+"\n");
				upperRight.setVisible(true);
				hasData = true;
				Collections.sort(dataSeriesListString);
				updateDataSeries();
				main.Main.window.packMe();
			}
		});
		
		
		
		//actionListener for dataSeries
		dataSeries.addActionListener(new ActionListener(){			
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println((String) dataSeries.getSelectedItem());
				if(hasData) {
					try {
						graph.resetScore();
					}catch(Exception e){
						System.out.println("No clear needed at first run");
					}
					textArea.append((data.JsonReader.readData((String) dataSeries.getSelectedItem()))+"\n");
					
					
					main.Main.window.packMe();
				}else {
					textArea.append("**no data to read**\n");
				}
			}
				
		});
		
		

		//actionListener for timeSeries
		timeSeries.addActionListener(new ActionListener(){			
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				
				if (timeSeries.getSelectedItem() == "TIME_SERIES_INTRADAY") {
					timeInterval.enable();
					outputSize.enable();
				}else {
					timeInterval.disable();
					outputSize.disable();
				}

				
				main.Main.window.packMe();
			}
				
		});
		
		
		
		
		
		
		//graph
		graph = new Graph();
		GridBagConstraints d = new GridBagConstraints();
		d.anchor = GridBagConstraints.FIRST_LINE_START;
		d.weightx = 0.5;
		d.fill = GridBagConstraints.NORTH;
		

		/*d.gridx = 0;
		d.gridy = 0;
		upperRight.add(new Label((String) dataSeries.getSelectedItem()), d);*/
		
		d.gridx = 0;
		d.gridy = 1;
		upperRight.add(graph, d);
		

		this.packMe();
		this.setVisible(true);
	}
	
	public void packMe() {
		this.repaint();
		this.revalidate();
		this.setVisible(true);
	}
	
	
	public void resetDataSeries() {
		dataSeriesListString.clear();
	}
	
	public void setDataSeries(String dataSeries){
		dataSeriesListString.add(dataSeries);
	}
	public void updateDataSeries() {
		dataSeries.removeAllItems();
		int itemCount = dataSeries.getItemCount();
		for (int i = 0; i < dataSeriesListString.size(); i++) {
			dataSeries.insertItemAt(dataSeriesListString.get(i), i);
			//dataSeries.removeItemAt(i+1);
		}
		if(itemCount > dataSeriesListString.size()) {
			for (int i = dataSeriesListString.size(); i < itemCount; i++) {
				dataSeries.removeItemAt(i);
			}
		}
	}

	public boolean DataSeriesEmpty() {
		return dataSeriesListString.isEmpty();
	}
	
}

