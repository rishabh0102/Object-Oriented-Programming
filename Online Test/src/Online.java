import java.awt.event.*;

import javax.swing.*;

import java.awt.*;
import java.awt.geom.*;

// class implementing the icon
class CircleIcon implements Icon
{
	private double		radius;			// radius of the circle icon
	private int			X_position;		// x-position of circle icon
	private int			Y_position;		// y-position of circle icon
	// Constructor Method
	CircleIcon(double radius)
	{
		this.radius 		= 	radius;
		this.X_position 	= 	300;	// current x-position is 300
		this.Y_position 	= 	0;		// current y-position is 0
	}

	// Methods for getting icon height and width
	public int getIconWidth()			{	return (int)radius;   	}
	public int getIconHeight()			{	return (int)radius;   	}

	// Method for painting icon
	public void paintIcon(Component C,Graphics g, int x,int y)
	{
	Graphics2D g2 = (Graphics2D)g;
	Ellipse2D.Double circle = new Ellipse2D.Double(x+X_position,y+Y_position,radius,radius);
	g2.draw(circle);
	g2.setColor(Color.red);
	g2.fill(circle);
	}

	//    ADD ANY OTHER METHOD(s) THAT YOU THINK IS/ARE REQUIRED FOR THIS PROBLEM. DO NOT REMOVE
	//    THESE COMMENTS JUST WRITE YOUR CODE AFTER THESE COMMENTS
	public double getRadius()			{ return this.radius; }
	public int    getX()				{ return this.X_position;}
	public int    getY()				{ return this.Y_position;}

	public void   incrementX()			{ X_position++;	}
	public void   decrementX()			{ X_position--;	}
	public void   incrementRadius()			{ radius++;	}
	public void   decrementRadius()			{ radius--;	}


}// End of class CircleIcon


//-----------------------------------------FOUR THREAD CLASSES -------------------------

class LeftThread extends Thread
{
	private JFrame frame;
	private CircleIcon circle;
	private 	JLabel		label;
	private		boolean		suspendFlag=false;
	LeftThread(JFrame frame, CircleIcon circle, JLabel label)
	{
		this.frame = frame;
		this.circle= circle;
		this.label = label;
	}
	public void mysuspend() {
		suspendFlag = true; 
		}
	public synchronized void myresume()		{ 
		suspendFlag = false;
		notify();
		}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			while(true){
				synchronized (this) {
					while(suspendFlag) {
						wait();
						
					}
					if(circle.getRadius() <= circle.getX()){
						circle.decrementX();
						label.setIcon(circle);
						frame.repaint();
						Thread.sleep(100);

					}
					else{
						mysuspend();
					}
					
					
				}
			}
		}
		catch(InterruptedException e){
			
		}
	
	}


						// COMPLETE THE CODE FOR THIS CLASS
}

class RightThread extends Thread
{
	private JFrame frame;
	private CircleIcon circle;
	private JLabel label;
	private		boolean		suspendFlag=false;
	
	public RightThread(JFrame frame, CircleIcon circle,JLabel label) {
		this.frame = frame;
		this.circle = circle;
		this.label = label;
		// TODO Auto-generated constructor stub
	}
	public void mysuspend() 			{ suspendFlag = true; }
	public synchronized void myresume()	{ 
		suspendFlag = false;
		notify();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			while(true){
				synchronized (this) {
					while(suspendFlag){
						wait();
					}
					if(circle.getX()<600-circle.getRadius()){
						
					circle.incrementX();
					label.setIcon(circle);
					frame.repaint();
					Thread.sleep(100);
					
					
				}
					else{
						mysuspend();
					}
			}
	}
		}
		catch(InterruptedException e){}
		
	}
}
	
						// COMPLETE THE CODE FOR THIS CLASS

		
class ScaleUpThread extends Thread
{
	private JFrame frame;
	private CircleIcon circle;
	private JLabel label;
	private boolean suspendFlag = false;
	public ScaleUpThread(JFrame frame, CircleIcon circle, JLabel label) {
		this.frame = frame;
		this.circle = circle;
		this.label = label;
	}
	public void mysuspend(){
		suspendFlag = true;
	}
	public synchronized void myresume(){
		suspendFlag = false;
		notify();
	}
	@Override
	public void run() {
		try{
			while(true){
				synchronized (this) {
					while(suspendFlag){
						wait();	
					}
					if(circle.getRadius()<=50){
						circle.incrementRadius();
						label.setIcon(circle);
						frame.repaint();
						Thread.sleep(100);
					}
					else{
						mysuspend();
					}
					
					
					
					
				}
			}
				
			}
		catch(InterruptedException e ){}
								// TODO Auto-generated method stub
	}
	
						// COMPLETE THE CODE FOR THIS CLASS
}
class ScaleDownThread extends Thread
{
	private JFrame frame;
	private CircleIcon circle;
	private JLabel label;
	private boolean suspendFlag = false;
	public ScaleDownThread(JFrame frame, CircleIcon circle,JLabel label) {
		this.frame = frame;
		this.label = label;
		this.circle = circle;
	}
	public void mysuspend(){
		suspendFlag = true;
	}
	public synchronized void myresume(){
		suspendFlag = false;
		notify();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			while(true){
				synchronized (this) {
					while(suspendFlag){
						wait();
						
					}
					if(circle.getRadius()>=5){
						circle.decrementRadius();
						label.setIcon(circle);
						frame.repaint();
						Thread.sleep(100);
					}
					else{
						mysuspend();
					}
					
				}
			}
	}
		catch(InterruptedException e){}
	
	
						// COMPLETE THE CODE FOR THIS CLASS
}
}

//----------------------------------------THREAD CLASSES ENDS HERE------------------------




// Driver class
class Online
{
	// The following four boolean class variables can be used to determine whether the thread has started or not
	// While starting any thread just make the corresponding flag as  true. Initially no thread is started.
	public static boolean leftThreadStarted 		= false;	//leftThread not started initially
	public static boolean rightThreadStarted 		= false;	//rightThread not started initially
	public static boolean scaleUpThreadStarted 		= false;	//scaleUpThread not started initially
	public static boolean scaleDownThreadStarted 	= false;	//scaleDownThread not started initially

	// The following four boolean class variables can be used to determine which thread is currently running
	// Only one of the following 4 variables will be true at a time and rest other are false.
	// Initially no thread is running
	public static boolean leftThreadRunning 		= false;	//leftThread is not running initially
	public static boolean rightThreadRunning 		= false;	//rightThread is not running initially
	public static boolean scaleUpThreadRunning 		= false;	//scaleUpThread is not running initially
	public static boolean scaleDownThreadRunning 	= false;	//scaleDownThread is not running initially

	// Driver main() Method
	public static void main (String args[])
	{

		JFrame frame = new JFrame("Main Frame");	// original frame
		frame.setSize(600,600);						// size 600 * 600
		frame.setLayout(new BorderLayout());		// layout BorderLayout

		// Create a CircleIcon instance circleIcon with initial radius 10
		CircleIcon circleIcon = new CircleIcon(10);

		// Create a JLabel instance named iconLabel which can hold above circleIcon
		final JLabel iconLabel = new JLabel();
		iconLabel.setIcon(circleIcon);		// setting icon for iconLabel

		// Create a JPanel for holding iconLabel
		JPanel centrePanel = new JPanel(new BorderLayout());
		centrePanel.add(iconLabel,BorderLayout.CENTER);		// Adding iconLabel in the center of centrePanel

		// Create Buttons with labels as mentioned in the Question
		JButton leftButton 			= 	new JButton("LEFT");		// left button
		JButton rightButton 		= 	new JButton("RIGHT");		// right button
		JButton scaleUpButton 		= 	new JButton("SCALE UP");	// scaleUp button
		JButton scaleDownButton 	= 	new JButton("SCALE DOWN");	// scaleDown button
		JButton stopButton 			= 	new JButton("STOP");		// stop button

		// Create a buttonPanel for holding buttons
		JPanel buttonPanel		=	new JPanel();

		// Add buttons to buttonPanel
		buttonPanel.add(leftButton);
		buttonPanel.add(rightButton);
		buttonPanel.add(scaleUpButton);
		buttonPanel.add(scaleDownButton);
		buttonPanel.add(stopButton);


		// Add centrePanel to center of frame
		frame.add(centrePanel,BorderLayout.CENTER);

		// Add buttonPanel to south portion of frame
		frame.add(buttonPanel,BorderLayout.SOUTH);

		// Display Frame
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// Default close operation

		//---------------------------------------- WRITE YOUR CODE FROM HERE---------------------------

		// Complete the following thread creating statement(s) after removing the comments

		 final LeftThread leftThread 				= 	new LeftThread(frame,circleIcon,iconLabel);
		 final RightThread rightThread 			    = new RightThread(frame,circleIcon,iconLabel);

		 final ScaleUpThread scaleUpThread 		= 	new ScaleUpThread(frame,circleIcon,iconLabel);

		 final ScaleDownThread scaleDownThread 	= 	new ScaleDownThread(frame,circleIcon,iconLabel);



		// WRITE JAVA CODE FOR ACTIONLISTENERS FOR VARIOUS BUTTONS. USE ONLY INNER CLASSES FOR THE SAME
		leftButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(rightThreadRunning) 		{ rightThread.mysuspend(); 	rightThreadRunning   = false;	}
				if(scaleUpThreadRunning) 	{ scaleUpThread.mysuspend();	scaleUpThreadRunning = false;	}
				if(scaleDownThreadRunning) 	{ scaleDownThread.mysuspend();	scaleDownThreadRunning = false;	}
				if(leftThreadStarted == false)
				{ leftThreadStarted = true;leftThread.start();leftThreadRunning = true;	return;	}
				if(leftThreadRunning == false) 	{ leftThread.myresume(); leftThreadRunning = true; }
				}
	
		});
		rightButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
			if(leftThreadRunning) 		{ leftThread.mysuspend(); 	leftThreadRunning    = false;	}
			if(scaleUpThreadRunning) 	{ scaleUpThread.mysuspend();	scaleUpThreadRunning = false;	}
			if(scaleDownThreadRunning) 	{ scaleDownThread.mysuspend();	scaleDownThreadRunning = false;	}
			if(rightThreadStarted == false)
			{ rightThreadStarted = true;rightThread.start();rightThreadRunning = true;	return;	}
			if(rightThreadRunning == false) 	{ rightThread.myresume(); rightThreadRunning = true; }
			}
		});

		scaleUpButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
			if(rightThreadRunning) 		{ rightThread.mysuspend(); 	rightThreadRunning   = false;	}
			if(leftThreadRunning) 		{ leftThread.mysuspend();	leftThreadRunning    = false;	}
			if(scaleDownThreadRunning) 	{ scaleDownThread.mysuspend();	scaleDownThreadRunning = false;	}
			if(scaleUpThreadStarted == false)
			{ scaleUpThreadStarted = true;scaleUpThread.start();scaleUpThreadRunning = true;	return;	}
			if(scaleUpThreadRunning == false) 	{ scaleUpThread.myresume(); scaleUpThreadRunning = true;}
			}
		});

		scaleDownButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
			if(rightThreadRunning) 		{ rightThread.mysuspend(); 	rightThreadRunning   = false;	}
			if(scaleUpThreadRunning) 	{ scaleUpThread.mysuspend();	scaleUpThreadRunning = false;	}
			if(leftThreadRunning) 		{ leftThread.mysuspend();	leftThreadRunning    = false;	}
			if(scaleDownThreadStarted == false)
			{ scaleDownThreadStarted = true;scaleDownThread.start();scaleDownThreadRunning = true;	return;	}
			if(scaleDownThreadRunning == false) 	{ scaleDownThread.myresume(); scaleDownThreadRunning = true; }
			}
		});
		stopButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
			if(rightThreadRunning) 		{ rightThread.mysuspend(); 	rightThreadRunning   = false;	}
			if(scaleUpThreadRunning) 	{ scaleUpThread.mysuspend();	scaleUpThreadRunning = false;	}
			if(leftThreadRunning) 		{ leftThread.mysuspend();	leftThreadRunning    = false;	}
			if(scaleDownThreadRunning) 	{ scaleDownThread.mysuspend();	scaleDownThreadRunning = false;	}
			}
		});

	} // End of main() Method
}// End of class OnlineMain


