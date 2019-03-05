

import java.awt.GraphicsConfiguration;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class Window extends JFrame {
	private static final long serialVersionUID = 1L;
	
	//static GraphicsConfiguration gc;
	
	
	
	private final JSplitPane splitPane;  	// split the window in right and left
	private final JSplitPane leftSplitPane;
    private final JPanel upperRight;      	// container panel for upper right side
    private final JPanel upperLeft;    		// container panel for upper left side
    //private final JPanel downRight; 		// container panel for upper right side
    private final JPanel downLeft;			// container panel for down left side
    public static Graph graph;						// our graph
    //private final JScrollPane scrollPane; // makes the text scrollable
    //private final JTextArea textArea;     // the text
    //private final JPanel inputPanel;      // under the text a container for all the input elements
    //private final JTextField textField;   // a textField for the text the user inputs
    //private final JButton button;         // and a "send" button
	
	public Window(){
		
		splitPane = new JSplitPane();
		leftSplitPane = new JSplitPane();
		
		
		upperLeft = new JPanel(new GridBagLayout());
		//splitPane.add(upperLeft);
		
		
		upperRight = new JPanel(new GridBagLayout());
		//splitPane.add(upperRight);

		
		//downRight = new JPanel(new GridBagLayout());
		//splitPane.add(downRight);
		
		
		downLeft = new JPanel();
		//splitPane.add(downLeft);
		
		
		this.setLayout(new GridLayout());
		this.add(splitPane);
		

		leftSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);  // we want it to split the window horizontally
		leftSplitPane.setDividerLocation(300);                    	// the initial position of the divider is 400 (our window is 800 pixels high)
		leftSplitPane.setTopComponent(upperLeft);                  	// at the top we want our "topPanel"
		leftSplitPane.setBottomComponent(downLeft);
		
		
		splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);  // we want it to split the window horizontally
        splitPane.setDividerLocation(400);                    	// the initial position of the divider is 400 (our window is 800 pixels high)
        splitPane.setTopComponent(leftSplitPane);                  	// at the top we want our "topPanel"
        splitPane.setBottomComponent(upperRight);
		
		this.setTitle("My graph");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1200, 800);
		this.setResizable(true);
		
		this.setLocationRelativeTo(null);
		
		JButton button1 = new JButton("--- Do query ---");		//new button
		
		
		String[] dataSeriesString = {"1. open", "2. high", "3. low", "4. close", "5. volume"};
		JComboBox<String> dataSeries = new JComboBox<String>(dataSeriesString);	//5 dropdownboxes

		String[] timeSeriesString = {"TIME_SERIES_INTRADAY", "TIME_SERIES_DAILY_ADJUSTED", "TIME_SERIES_WEEKLY", 
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
		
	
		JTextArea textArea = new JTextArea (25,33);
		JScrollPane scroll = new JScrollPane(textArea);
		textArea.setEditable(false);
		//textArea.append("\nHey gorgeous\n\n\nYou look nice today\n\n\nHope you will have a wonderful day\n\n\nWe sure as hell will at prog III\n\n\nLOL\n\n\n...\n\n\n...\n\n\nThat's enough for one day\n\nI'm outta here\n\n\nGoodbye");
		
		//downLeft.add(textArea);
		
		downLeft.add(scroll,c);
		
		
		
		button1.addActionListener(new ActionListener(){			//settings for what happens when we push "Do query"-button (button1)
			
			int size = 0;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					//graph.resetScore();
				}catch(Exception e){
					System.out.println("No clear needed at first run");
				}
				textArea.append((JsonReader.readWeb((String) dataSeries.getSelectedItem(), (String) timeSeries.getSelectedItem(), (String) symbol.getSelectedItem(), (String) timeInterval.getSelectedItem(), (String) outputSize.getSelectedItem()))+"\n");
				//upperRight.setVisible(true);
				size++;
				System.out.println("You have pushed the button " + size + " times");
			}	
		});
		

		graph = new Graph();
		GridBagConstraints d = new GridBagConstraints();
		d.anchor = GridBagConstraints.FIRST_LINE_START;
		d.weightx = 0.5;
		//c.fill = GridBagConstraints.NORTH;
		upperRight.add(graph, d);
		
		this.setVisible(true);
		
	//	this.pack();
	}
}

