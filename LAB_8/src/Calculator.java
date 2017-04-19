import javax.swing.*;
import java.awt.*;
public class Calculator extends JFrame {
	JTextField resultField;
	Calculator(){
		super("Calculator");
		JPanel p = new JPanel();
		JPanel p1 = new JPanel(); 
		JPanel p2 = new JPanel(); 
		JPanel p3 = new JPanel(); 
		p.setSize(100, 100);
		
		JLabel titleLabel = new JLabel("Calculator", JLabel.CENTER);
		resultField = new JTextField(10);
		JButton b1 = new JButton("1"); 
		JButton b2 = new JButton("2"); 
		JButton b3 = new JButton("3"); 
		JButton b4 = new JButton("4");
		JButton b5 = new JButton("5"); 
		JButton b6 = new JButton("6"); 
		JButton b7 = new JButton("7"); 
		JButton b8 = new JButton("8"); 
		JButton b9 = new JButton("9"); 
		JButton b0 = new JButton("0");
		JButton bpo = new JButton(".");
		JButton be = new JButton("="); 
		JButton bp = new JButton("+"); 
		JButton bs = new JButton("-");
		JButton bm = new JButton("*"); 
		JButton bd = new JButton("/"); 
		JButton bclr = new JButton("AC");
		
		p.setLayout(new BorderLayout());
		p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
		p2.setLayout(new GridLayout(4, 4));
		add(p);
		p1.add(titleLabel);
		p1.add(resultField); 
		p.add(BorderLayout.NORTH, p1);
		p2.add(b1);
		p2.add(b2);
		p2.add(b3);
		p2.add(bp);
		p2.add(b4);
		p2.add(b5);
		p2.add(b6);
		p2.add(bs);
		p2.add(b7);
		p2.add(b8);
		p2.add(b9);
		p2.add(bm);
		p2.add(b0);
		p2.add(bpo);
		p2.add(be);
		p2.add(bd);
		p3.add(bclr);
		p.add(BorderLayout.CENTER, p2); 
		p.add(BorderLayout.SOUTH, p3);

	}
	public static void main(String[] args) {
		JFrame frame = new Calculator(); frame.setVisible(true);
		// make program quit when you close the window
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		          frame.setSize(250, 250);
		     }
}


