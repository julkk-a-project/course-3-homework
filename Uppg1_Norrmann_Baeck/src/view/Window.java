package view;

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
    public final JPanel upperRight;      	// container panel for graph
    private final JPanel upperLeft;    		// container panel for buttons
    private final JPanel downLeft;			// container panel for textArea
    public Graph graph;						// our graph
    public JTextArea textArea;     			// the textArea
    public JButton doQueryButton;			// our DoQuery button ;)
    
    public List<String> dataSeriesListString;	//ListArray used as temp storage for dataSeries
    public JComboBox<String> dataSeries;		//dataSeries alternatives
	public JComboBox<String> timeSeries;		//  ---||---
	public JComboBox<String> symbol;
	public JComboBox<String> timeInterval;
	public JComboBox<String> outputSize;
    
    boolean hasData = false;


	
    //constructor
	public Window(){
		
		
		
		splitPane = new JSplitPane();
		leftSplitPane = new JSplitPane();
		
		
		//to split window
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
		
		doQueryButton = new JButton("--- Do query ---");		//new button
		
		
		//5 dropDownboxes dataSeriesString, timeSeriesString, symbolString, timeIntervalString and outpusizeString
		
		//initializes dataSeries list:
		dataSeriesListString = new ArrayList<String>();
		//String[] dataSeriesString = {"1. open", "2. high", "3. low", "4. close", "5. volume"};
		dataSeries = new JComboBox<String>();	
		
		//4 "static" comboBoxes:
		//String[] timeSeriesString = {"TIME_SERIES_INTRADAY", "TIME_SERIES_DAILY", "TIME_SERIES_DAILY_ADJUSTED", "TIME_SERIES_WEEKLY", 
		//		"TIME_SERIES_WEEKLY_ADJUSTED", "TIME_SERIES_MONTHLY", "TIME_SERIES_MONTHLY_ADJUSTED"};
		timeSeries = new JComboBox<String>(main.Main.inis.getKeyValue("TIME_SERIES"));

		//String[] symbolString = {"AAPL", "GOOG", "INTC", "KO", "MSFT", "WMT"};
		symbol = new JComboBox<String>(main.Main.inis.getKeyValue("SYMBOL"));

		//String[] timeIntervalString = {"1min", "5min", "15min", "30min", "60min"};
		timeInterval = new JComboBox<String>(main.Main.inis.getKeyValue("TIME_INTERVAL"));

		//String[] outputSizeString = {"compact", "full"};
		outputSize = new JComboBox<String>(main.Main.inis.getKeyValue("OUTPUT_SIZE"));
		
		
		

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
		upperLeft.add(doQueryButton, c);
		
		
		
		//textArea
		textArea = new JTextArea (25,33);
		JScrollPane scroll = new JScrollPane(textArea);
		textArea.setEditable(false);
		
		//position for textArea
		downLeft.add(scroll,c);
		
		
		
		
		
		

		//actionListener for timeSeries
		timeSeries.addActionListener(new ActionListener(){			
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//timeInterval & outputSize visible for intraday and not visible for other options
				boolean show;
				if (timeSeries.getSelectedItem() == "TIME_SERIES_INTRADAY") {
					show = true;
				}else {
					show = false;
				}
				timeInterval.setEnabled(show);	
				outputSize.setEnabled(show);
				
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
	
	//to get preferred size on content
	public void packMe() {
		this.repaint();
		this.revalidate();
		this.setVisible(true);
	}
	
	
	//to reset dataSeries
	public void resetDataSeries() {
		dataSeriesListString.clear();
	}
	
	//to set dataSeries
	public void setDataSeries(String dataSeries){
		dataSeriesListString.add(dataSeries);
	}
	
	//to update dataSeries
	public void updateDataSeries() {
		dataSeries.removeAllItems();
		int itemCount = dataSeries.getItemCount();
		
		for (int i = 0; i < dataSeriesListString.size(); i++) {
			dataSeries.insertItemAt(dataSeriesListString.get(i), i);
			//dataSeries.removeItemAt(i+1);
		}
		
		if(itemCount > dataSeriesListString.size()) {
			for (int i = dataSeriesListString.size(); i > itemCount; i++) {
				dataSeries.removeItemAt(i);
			}
		}
	}

	//to check if dataSeries is empty
	public boolean DataSeriesEmpty() {
		return dataSeriesListString.isEmpty();
	}	
}