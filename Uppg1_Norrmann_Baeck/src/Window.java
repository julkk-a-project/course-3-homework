

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



public class Window extends JFrame {
	private static final long serialVersionUID = 1L;
	
	//static GraphicsConfiguration gc;
	
	
	
	private final JSplitPane splitPane;  	// split the window in right and left
	private final JSplitPane leftSplitPane;
    private final JPanel upperRight;      	// container panel for upper right side
    private final JPanel upperLeft;    		// container panel for upper left side
    private final JPanel downLeft;			// container panel for down left side
    //private final JScrollPane scrollPane; // makes the text scrollable
    //private final JTextArea textArea;     // the text
    //private final JPanel inputPanel;      // under the text a container for all the input elements
    //private final JTextField textField;   // a textField for the text the user inputs
    //private final JButton button;         // and a "send" button
	
	public Window(){

		this.setTitle("My graph");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1200, 800);
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		
		splitPane = new JSplitPane();
		leftSplitPane = new JSplitPane();
		
		
		upperLeft = new JPanel(new GridBagLayout());
		//splitPane.add(upperLeft);
		
		
		upperRight = new JPanel(new GridBagLayout());
		//splitPane.add(upperRight);
		
		
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
		
		
		JButton button1 = new JButton("--- Do query ---");		//new button
		
		JComboBox<Object> dropDown1 = new JComboBox<Object>();	//5 dropdownboxes
		JComboBox<Object> dropDown2 = new JComboBox<Object>();
		JComboBox<Object> dropDown3 = new JComboBox<Object>();
		JComboBox<Object> dropDown4 = new JComboBox<Object>();
		JComboBox<Object> dropDown5 = new JComboBox<Object>();
		
		

		
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
		upperLeft.add(dropDown1, c);
		
		c.gridx = 1;
		c.gridy = 1;
		upperLeft.add(dropDown2, c);
		
		c.gridx = 1;
		c.gridy = 2;
		upperLeft.add(dropDown3, c);
		
		c.gridx = 1;
		c.gridy = 3;
		upperLeft.add(dropDown4, c);
		
		c.gridx = 1;
		c.gridy = 4;
		upperLeft.add(dropDown5, c);

		c.gridx = 1;
		c.gridy = 5;
		upperLeft.add(button1, c);
		
	
		JTextArea textArea = new JTextArea (27,35);
		JScrollPane scroll = new JScrollPane(textArea);
		textArea.setEditable(false);
		//textArea.append("\nHey gorgeous\n\n\nYou look nice today\n\n\nHope you will have a wonderful day\n\n\nWe sure as hell will at prog III\n\n\nLOL\n\n\n...\n\n\n...\n\n\nThat's enough for one day\n\nI'm outta here\n\n\nGoodbye");
		
		//downLeft.add(textArea);
		
		

		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		downLeft.add(scroll,c);
		
		
		
		
		button1.addActionListener(new ActionListener(){			//settings for what happens when we push "Do query"-button (button1)
			int size = 0;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				textArea.append(("\n"+JsonReader.readWeb()));
				size++;
				System.out.println("You have pushed the button " + size + " times");
			}	
		});
		
		
		this.setVisible(true);
		
	//	this.pack();
	}
}

