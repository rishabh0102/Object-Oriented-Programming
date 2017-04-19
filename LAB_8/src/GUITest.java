import java.awt.*;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class GUITest implements ActionListener {
JFrame frame;
JPanel commentPanel;
JButton button1;
JTextArea text1,text2;
JLabel label;
GUITest() {
frame=new JFrame();//create a new frame
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exists when you close the window

//set the default size of the frame 
frame.setSize(700,700);
//Display the frame on the screen
frame.setVisible(true);
//creating panel that contains label, text area and //button
commentPanel= new JPanel();
commentPanel.setLayout(null);
label=new JLabel("Your feedback :");
label.setLocation(50, 25);
label.setSize(100, 30);
commentPanel.add(label);
text1=new JTextArea();
text1.setLocation(50, 50);
text1.setSize(500, 100); 
commentPanel.add(text1);
text2 = new JTextArea();
text2.setLocation(50,300);
text2.setSize(500, 100); 
commentPanel.add(text2);
text2.setEnabled(false);
button1 =new JButton("Submit");
button1.setLocation(50,175);
button1.setSize(100, 30);
button1.addActionListener(this);
commentPanel.add(button1);
frame.add(commentPanel);
frame.validate();
}
public static void main(String args[]){ 
	GUITest gui=new GUITest();
}
public void actionPerformed(ActionEvent e){ 
	String command = e.getActionCommand();
	if( command.equals( "Submit" )){
		button1.setEnabled(false);
		text1.setEnabled(false);
//		text2.setEnabled(true);

		String s = text1.getText();
		text2.setText(s);
		text2.setDisabledTextColor(Color.RED);
	}
	//see (1)in exercise 2.1
	
}
}