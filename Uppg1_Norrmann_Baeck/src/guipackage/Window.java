package guipackage;

import java.awt.GraphicsConfiguration;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;



public class Window extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	static GraphicsConfiguration gc;
	
	public Window(){
		this.setTitle("My graph");
		this.setSize(800, 400);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		JButton button1 = new JButton("--- Do query ---");
		button1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("You pushed the button");	
			}	
		});
		
		button1.setBounds(100, 100, 140, 40);
		this.add(button1);
		
		JTextArea textArea = new JTextArea (5,20);
		JScrollPane scroll = new JScrollPane(textArea);
		textArea.setEditable(false);
		//this.add(textArea);
		//this.add(scroll);
	}
}

