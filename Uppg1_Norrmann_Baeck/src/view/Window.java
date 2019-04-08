package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

public class Window extends JFrame {
	
	 
	private static final long serialVersionUID = 1L;
	
	
	private final JSplitPane splitPane;  		// split the window in right and left
	private final JSplitPane leftSplitPane; 	// splits textArea from buttons
    public final JPanel upperRight;      		// container panel for graph
    private final JPanel upperLeft;    			// container panel for buttons
    private final JPanel downLeft;				// container panel for textArea
    
    public Graph graph;							// our graph
    public JTextArea textArea;     				// the textArea
    public JTextArea apiTextArea;    			// the textArea for API Key
    public JTextArea startDateTextArea;    		// the textArea for startDate
    public JTextArea endDateTextArea;    		// the textArea for stopDate
    public JTextArea errorTextArea;    			// the textArea for stopDate
    public JButton doQueryButton;				// our DoQuery button ;) 
    public List<String> dataSeriesListString;	//ListArray used as temp storage for dataSeries
    
    public JComboBox<String> dataSeries;		//dataSeries alternatives
	public JComboBox<String> timeSeries;		//  ---||---
	public JComboBox<String> symbol1;			//  ---||---
	public JComboBox<String> symbol2;			//  ---||---
	public JComboBox<String> timeInterval;		//  ---||---
	public JComboBox<String> outputSize;		//  ---||---
    
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
		this.setSize(1920, 1080); 		//1080p
		this.setResizable(true);
		
		this.setLocationRelativeTo(null);
		
		
		
		//apiTextArea
		apiTextArea = new JTextArea (main.Main.inis.getKeyValueInt(0,"API_KEY"), 1,25);
		apiTextArea.setEditable(true);
				
		
		
		//5 dropDownboxes dataSeriesString, timeSeriesString, symbolString, timeIntervalString and outpusizeString
		//initializes dataSeries list:
		dataSeriesListString = new ArrayList<String>();
		dataSeries = new JComboBox<String>();	
		
		//4 "static" comboBoxes:
		timeSeries = new JComboBox<String>(main.Main.inis.getKeyValue("TIME_SERIES"));
		
		
		String[] temp = main.Main.inis.getKeyValue("SYMBOL");
		String[] symlist = new String[temp.length+1];
		symlist[0] = "";
		for (int i = 1; i < temp.length; i++) {
			symlist[i] = main.Main.inis.getKeyValueInt(i, "SYMBOL");	
		}
		symbol1 = new JComboBox<String>(symlist);
		symbol2 = new JComboBox<String>(symlist);
		
		
				
		timeInterval = new JComboBox<String>(main.Main.inis.getKeyValue("TIME_INTERVAL"));
		outputSize = new JComboBox<String>(main.Main.inis.getKeyValue("OUTPUT_SIZE"));
		
		
		
		//startDate TextArea
		startDateTextArea = new JTextArea ("Enter date here...", 1,10);
		startDateTextArea.setEditable(true);
			
		//stopDate TextArea
		endDateTextArea = new JTextArea ("...and here", 1,10);
		endDateTextArea.setEditable(true);
				
		
		
		//---Do query--- button
		doQueryButton = new JButton("--- Do query ---");
		
		
		
		//errorTextArea
		errorTextArea = new JTextArea("Date (not) read", 1,25);
		errorTextArea.setEditable(false);
		
		

		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.weightx = 0.5;
		c.fill = GridBagConstraints.NORTH;
		

		
		//positions for the labels of API key text area and dropDownBoxes
		c.gridx = 0;
		c.gridy = 0;
		upperLeft.add(new Label("API Key"), c);
		
		c.gridx = 0;
		c.gridy = 1;
		upperLeft.add(new Label("Data Series"), c);
		
		c.gridx = 0;
		c.gridy = 2;
		upperLeft.add(new Label("Time Series"), c);
		
		c.gridx = 0;
		c.gridy = 3;
		symbol1.setForeground(view.Graph.getStrokeColor1());
		symbol1.setBackground(view.Graph.getOvalColor1());
		upperLeft.add(new Label("Symbol 1"), c);
		
		c.gridx = 0;
		c.gridy = 4;
		symbol2.setForeground(view.Graph.getStrokeColor2());
		symbol2.setBackground(view.Graph.getOvalColor2());
		upperLeft.add(new Label("Symbol 2"), c);

		c.gridx = 0;
		c.gridy = 5;
		upperLeft.add(new Label("Time Interval"), c);
		
		c.gridx = 0;
		c.gridy = 6;
		upperLeft.add(new Label("Output Size"), c);
		
		
		c.gridx = 0;
		c.gridy = 7;
		upperLeft.add(new Label("Date interval"), c);
		
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 8;
		//upperLeft.add(new Label("Date errors"), c);
		
		
		
		//position for our textAreas and dropDownBoxes
		c.gridwidth = 3;
		c.gridx = 1;
		c.gridy = 0;
		upperLeft.add(apiTextArea, c);
		
		c.gridx = 1;
		c.gridy = 1;
		upperLeft.add(dataSeries, c);
		
		c.gridx = 1;
		c.gridy = 2;
		upperLeft.add(timeSeries, c);
		
		c.gridx = 1;
		c.gridy = 3;
		upperLeft.add(symbol1, c);
		
		c.gridx = 1;
		c.gridy = 4;
		upperLeft.add(symbol2, c);
		
		c.gridx = 1;
		c.gridy = 5;
		upperLeft.add(timeInterval, c);
		
		c.gridx = 1;
		c.gridy = 6;
		upperLeft.add(outputSize, c);	

		c.gridwidth = 2;
		c.gridx = 2;
		c.gridy = 8;
		upperLeft.add(errorTextArea, c);
		
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 8;
		upperLeft.add(doQueryButton, c);
		
		
		
		//to get dateInput on same level
		GridBagConstraints e = new GridBagConstraints();
		e.anchor = GridBagConstraints.FIRST_LINE_START;
		e.weightx = 0.5;
		e.fill = GridBagConstraints.NORTH;

		e.gridx = 1;
		e.gridy = 7;
		upperLeft.add(startDateTextArea, e);
		
		e.gridx = 2;
		e.gridy = 7;
		upperLeft.add(new Label("-"), e);
		
		e.gridx = 3;
		e.gridy = 7;
		upperLeft.add(endDateTextArea, e);
		
		
		
		//textArea Logg
		textArea = new JTextArea (25,33);
		JScrollPane scroll = new JScrollPane(textArea);
		textArea.setEditable(false);
		
		//position for textArea Logg
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
		
		//position for graph
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
	
	
	
	//to get dataSeries for end-user satisfaction
	public String getDataSeries(int i) {
		return dataSeriesListString.get(i);
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
	public boolean dataSeriesEmpty() {
		return dataSeriesListString.isEmpty();
	}

	public void DEBUG_READ_DATA_SERIES() {


		System.out.println("-----");
		System.out.println("(In Window)" + dataSeriesListString.isEmpty());
		for (int i = dataSeriesListString.size()-1; i >= 0; i--) {
			System.out.println("In Window" + dataSeriesListString.get(i));
		}
		System.out.println("-----");
	}	
}