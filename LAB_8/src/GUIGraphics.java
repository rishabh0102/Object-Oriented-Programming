import javax.swing.*;
import java.awt.*;
public class GUIGraphics extends JFrame {
	GUIGraphics(){
		super("Graphics");
		setSize(500,500) ;
		setDefaultCloseOperation(EXIT_ON_CLOSE) ;
		show();
	}
	public void paint(Graphics g){
		g.drawRect(50, 50, 400, 300);
		g.drawOval(150, 150, 50, 50);//face of stick man
		g.drawLine(175, 200, 175, 275);//height of stick man
		g.drawLine(140,225,210,225);//hands of stick man
		g.drawLine(175,275,150,325);//left leg of stick man
		g.drawLine(175,275,200,325);//right leg of stick man
		g.setColor(Color.yellow);
		g.fillRect(250, 100, 100, 50);//yellow rectangle
		g.setColor(Color.orange);
		g.fillOval(280, 110, 40, 30);//orange oval
	}
	public static void main(String args[])
	{ GUIGraphics graphics=new GUIGraphics();
	}


}
