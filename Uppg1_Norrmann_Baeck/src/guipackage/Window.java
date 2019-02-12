package guipackage;

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
	
	
	
	private final JSplitPane splitPane;  // split the window in top and bottom
    private final JPanel upperRight;       // container panel for the top
    private final JPanel upperLeft;    // container panel for the bottom
    private final JPanel downRight;
    private final JPanel downLeft;
    //private final JScrollPane scrollPane; // makes the text scrollable
    //private final JTextArea textArea;     // the text
    //private final JPanel inputPanel;      // under the text a container for all the input elements
    //private final JTextField textField;   // a textField for the text the user inputs
    //private final JButton button;         // and a "send" button
	
	public Window(){
		
		splitPane = new JSplitPane();
		
		
		upperLeft = new JPanel(new GridBagLayout());
		//splitPane.add(upperLeft);
		
		
		upperRight = new JPanel(new GridBagLayout());
		//splitPane.add(upperRight);

		
		downRight = new JPanel(new GridBagLayout());
		//splitPane.add(downRight);
		
		
		downLeft = new JPanel(new GridBagLayout());
		//splitPane.add(downLeft);
		
		
		this.setLayout(new GridLayout());
		this.add(splitPane);
		
		splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);  // we want it to split the window vertically
        splitPane.setDividerLocation(400);                    // the initial position of the divider is 400 (our window is 800 pixels high)
        splitPane.setTopComponent(upperLeft);                  // at the top we want our "topPanel"
        splitPane.setBottomComponent(upperRight);
		
		this.setTitle("My graph");
		this.setSize(800, 400);
		this.setResizable(true);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		JButton button1 = new JButton("--- Do query ---");
		
		JComboBox<Object> dropDown1 = new JComboBox<Object>();
		JComboBox<Object> dropDown2 = new JComboBox<Object>();
		JComboBox<Object> dropDown3 = new JComboBox<Object>();
		JComboBox<Object> dropDown4 = new JComboBox<Object>();
		JComboBox<Object> dropDown5 = new JComboBox<Object>();
		
		
		
		
		
		button1.addActionListener(new ActionListener(){
			int size = 0;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("You have pushed the button " + size + " times");
				size++;
			}	
		});
		
		
		//button1.setBounds(1, 1, 14, 4);
		
		
		
		
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.weightx = 0.5;
		

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


		
		

		c.fill = GridBagConstraints.VERTICAL;
		c.gridx = 1;
		c.gridy = 0;
		upperLeft.add(dropDown1, c);
		
		c.fill = GridBagConstraints.VERTICAL;
		c.gridx = 1;
		c.gridy = 1;
		upperLeft.add(dropDown2, c);
		
		c.fill = GridBagConstraints.VERTICAL;
		c.gridx = 1;
		c.gridy = 2;
		upperLeft.add(dropDown3, c);
		
		c.fill = GridBagConstraints.VERTICAL;
		c.gridx = 1;
		c.gridy = 3;
		upperLeft.add(dropDown4, c);
		
		c.fill = GridBagConstraints.VERTICAL;
		c.gridx = 1;
		c.gridy = 4;
		upperLeft.add(dropDown5, c);

		c.fill = GridBagConstraints.VERTICAL;
		c.gridx = 1;
		c.gridy = 5;
		upperLeft.add(button1, c);
		
		
		this.pack();
		
		//this.add(button1);
		
		JTextArea textArea = new JTextArea (2,2);
		//JScrollPane scroll = new JScrollPane(textArea);
		textArea.setEditable(false);
		//this.add(textArea);
		//this.add(scroll);
		
		
	}
}

