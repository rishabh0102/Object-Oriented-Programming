import java.util.Random;
import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

class QuadraticEquation
{
private		int	quadNo;			// Quadratic Equation NO
private 	int 	a;			// A Field
private 	int 	b;			// B Field	
private 	int 	c;			// C Field
private 	boolean isSolved;		// Represents whether Roots have been computed or not
private		boolean	isRootsPossible;	// Represents whether Roots are possible or not
private	static	int	noofobjectsCreated = 0;	// Keeps up the count of the no of instances of this class created so far
private		double	root1;			// First 	Root
private		double	root2;			// Second 	Root

	QuadraticEquation(int a, int b, int c)
	{
		this.a	=	a;
		this.b	=	b;
		this.c	=	c;
		isSolved=	false;
		noofobjectsCreated++;
		quadNo	=	noofobjectsCreated;
	}

	public synchronized void computeRoots()
	{
		if( b*b - 4*a*c < 0) 
		{
			isRootsPossible = false;
			root1 = root2 = Double.NEGATIVE_INFINITY; 
			isSolved = true;
			return;
		}
		double disc	=	Math.sqrt(b*b - 4*a*c);
		root1 		= 	(-b+disc)/(2*a);
		root2 		= 	(-b-disc)/(2*a);
		isRootsPossible = 	true;
		isSolved 	= 	true;
		return;
	}
	public synchronized String toString()
	{
		return "Quadratic Equation No:="+quadNo+"A:= "+a+"B:= "+b+"C:= "+c+"Root 1:="+root1+"Root 2:="+root2;
	}
	public synchronized boolean getisSolved()
	{
		return isSolved;
	}
	public synchronized	int getNoofObjects()
	{
		return noofobjectsCreated;
	}
}// End of class QuadraticEquation

class QuadraticEquationQueue
{
private QuadraticEquation[]	quadQueue	=	new QuadraticEquation[10]; // queue with capacity 10
private	int	tail;	// tail value
private	int	head;	// head value
private	int	size;	// size field
	QuadraticEquationQueue()
	{
		head = tail = size =0;
	}
	public synchronized QuadraticEquation remove() throws InterruptedException
	{
		while(size == 0) wait();
		QuadraticEquation quad = quadQueue[head];
		head++;
		size--;
		if(head == quadQueue.length)
			head =0;
		notifyAll();
	return quad;
	}
	public synchronized void add(QuadraticEquation quad) throws InterruptedException
	{
		while(size == quadQueue.length) wait();
		quadQueue[tail] = quad;
		tail++;
		size++;
		if(tail == quadQueue.length)
		tail = 0;
		notifyAll();
	}
	public synchronized boolean isFull()
	{
	return size == quadQueue.length;
	}
	public synchronized boolean isEmpty()
	{
	return size == 0;
	}
}// End of class QuadraticEquationQueue

class OnlineMain
{
static Random 	r1 = new Random(20);			// Random instance to craete random integer
static boolean 	createThreadStarted	= false;	// indicates whether CreateThread has been started or not
static boolean 	createThreadStopped	= false;	// indicates whether CreateThread has been stopped or not
static boolean 	createThreadResumed	= false;	// indicates whether CreateThread has been resumed or not
static boolean 	computeThreadStarted	= false;	// indicates whether ComputeThread has been started or not
static boolean 	computeThreadStopped	= false;	// indicates whether ComputeThread has been stopped or not
static boolean 	computeThreadResumed	= false;	// indicates whether ComputeThread has been resumed or not

public static void main(String args[])
{
JFrame frame = new JFrame("QuadraticFrame");			// Main JFrame
Container c1 = frame.getContentPane();				// Container for Main Frame 
c1.setLayout(new BoxLayout(c1,BoxLayout.X_AXIS));		// Setting Layout for Container c1

// **************************************** CREATION OF LEFTSIDE PANEL ***************************
// Creating First of the LeftSidePanel
//JPanel firstRow	= new JPanel();
JLabel label1	= new JLabel("QUEUE FULL");
//firstRow.add(label1);

// Creating START and STOP Buttons for the LeftSidePanel
JButton b1 = new JButton("START");
JButton b2 = new JButton("STOP");

// Creating Labels for the LeftSidePanel
JLabel L1	= new JLabel("A =");
JLabel L2	= new JLabel("B =");
JLabel L3	= new JLabel("C =");
JLabel L4	= new JLabel("No of QuadraticEquations Created:"+0);

// Creating Button Panel for START and STOP Buttons and adding Buttons to this Panel
JPanel btnPanel = new JPanel();
btnPanel.add(b1);
btnPanel.add(b2);

// Creating Three JTextFields for the LeftSidePanel
final JTextField T1 	= new JTextField(6); 
final JTextField T2 	= new JTextField(6); 
final JTextField T3 	= new JTextField(6); 

T1.setText("0");
T2.setText("0");
T3.setText("0");

// Creating Panel for Label "A=" and its corresponding JTextField and adding JLabel and JTextField to it
JPanel P1 = new JPanel();
P1.add(L1);
P1.add(T1);

// Creating Panel for Label "B=" and its corresponding JTextField and adding JLabel and JTextField to it
JPanel P2 = new JPanel();
P2.add(L2);
P2.add(T2);

// Creating Panel for Label "C=" and its corresponding JTextField and adding JLabel and JTextField to it
JPanel P3 = new JPanel();
P3.add(L3);
P3.add(T3);

// A Separate Panel for Last Label of the left sidePanel
JPanel P4 = new JPanel();
P4.add(L4);

// Creating a Final Panel and adding all the above panels to this
JPanel leftPanel = new JPanel();
leftPanel.setLayout(new BoxLayout(leftPanel,BoxLayout.Y_AXIS));
leftPanel.add(label1);
leftPanel.add(btnPanel);
leftPanel.add(P1);
leftPanel.add(P2);
leftPanel.add(P3);
leftPanel.add(P4);

// **************************************** CREATION OF RIGHTSIDE PANEL *************************** 

// Creating rightPanel and adding label "QUEUE EMPTY" to it
JPanel rightPanel = new JPanel();
rightPanel.setLayout(new BoxLayout(rightPanel,BoxLayout.Y_AXIS));
JLabel L20 = new JLabel("QUEUE EMPTY");		// L20 Label is QUEUE EMPTY
rightPanel.add(L20);

// Creating and Adding Buttons for right side 
JPanel P21 = new JPanel();			// P21 is Button Panel for right side
JButton b3 = new JButton("START");
JButton b4 = new JButton("STOP");
P21.add(b3);
P21.add(b4);

rightPanel.add(P21);

// Creating 10 JLabel references for Result Display Area for RightSide Panel
JLabel[] resultLabels = new JLabel[10];
for(int i=0;i<10;i++)
resultLabels[i] = new JLabel();

// Adding Labels of Result Display Area to rightPanel one by one 
for(int i=0;i<10;i++)
rightPanel.add(resultLabels[i]);

// Creating Label "NO OF EQUATIONS SOLVED" and adding it into right Panel
JLabel L21 = new JLabel("NO OF EQUATIONS SOLVED:"+0);
rightPanel.add(L21);

// Creating Three Counter Labels for compute Threads and adding it into right Panel
JLabel L22 = new JLabel("By Thread 1:"+0);
rightPanel.add(L22);

JLabel L23 = new JLabel("By Thread 2:"+0);
rightPanel.add(L23);

JLabel L24 = new JLabel("By Thread 3:"+0);
rightPanel.add(L24);

// Finally Adding leftPanel and rightPanel to conatiner for Main Frame
c1.add(leftPanel);
c1.add(rightPanel);

frame.setSize(400,600); 				// setting size of JFrame
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 	// setting Default Close Operation
frame.setVisible(true); 				// Showing Jframe


// ***************** In main() method WRITE YOUR CODE FROM HERE *************************

ActionListener listener = new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Integer a = r1.nextInt(20);
		Integer b = r1.nextInt(20);
		Integer c = r1.nextInt(20);
		T1.setText(a.toString());
		T2.setText(b.toString());
		T3.setText(c.toString());
		return;

		
	}
};
final Timer timer = new Timer(100,listener);
final QuadraticEquationQueue queue = new QuadraticEquationQueue();
final CreateThread createThread1 = new CreateThread(T1, T2, T3, label1, L4, queue);
final ComputeThread compute1 = new ComputeThread(queue, resultLabels, L21, L22, L23, L24, 1, L20);
final ComputeThread compute2 = new ComputeThread(queue, resultLabels, L21, L22, L23, L24, 2, L20);
final ComputeThread compute3 = new ComputeThread(queue, resultLabels, L21, L22, L23, L24, 3, L20);

b1.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(!createThreadStarted){
			createThread1.start();
			createThreadStarted = true;
			timer.start();
			return;

		}
		if(createThreadStopped){
			createThread1.myresume();
			createThreadResumed = true;
			createThreadStopped = false;
			timer.restart();
		}
	}
});
b2.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(createThreadStarted || createThreadResumed){
			createThread1.mysuspend();
			createThreadResumed = false;
			createThreadStopped = true;
			timer.stop();
			
		}
		
	}
});
b3.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(!computeThreadStarted){
			compute1.start();
			compute2.start();
			compute3.start();
			computeThreadStarted = true;
			return;
			

		}
		if(computeThreadStopped){
			compute1.myresume();
			compute2.myresume();
			compute3.myresume();
			computeThreadResumed = true;
			computeThreadStopped = false;

		}
		
	}
});
b4.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(computeThreadStarted || computeThreadResumed){
			compute1.mysuspend();
			compute2.mysuspend();
			compute3.mysuspend();
			computeThreadResumed = false;
			computeThreadStopped = true;

		}
		
	}
});




} // End of main() method
} // End of class OnlineMain

class CreateThread extends Thread
{
	private JTextField t1;
	private JTextField t2;
	private JTextField t3;
	private JLabel queue_full_label;
	private JLabel label_equation_created;
	private QuadraticEquationQueue queue;
	private boolean	suspendFlag = false;
	public void mysuspend()
	{
	suspendFlag = true;
	}
	public synchronized void myresume()
	{
	suspendFlag = false;
	notify();
	}
	
	public CreateThread(JTextField t1, JTextField t2, JTextField t3,
			JLabel queue_full_label, JLabel label_equation_created,
			QuadraticEquationQueue queue) {
		this.t1 = t1;
		this.t2 = t2;
		this.t3 = t3;
		this.queue_full_label = queue_full_label;
		this.label_equation_created = label_equation_created;
		this.queue = queue;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			while(true){
				synchronized (this) {
					while(suspendFlag){ wait();}
					try{
					int a = Integer.parseInt(t1.getText());
					int b = Integer.parseInt(t2.getText());
					int c = Integer.parseInt(t3.getText());
					if(a == 0) {continue;}
					QuadraticEquation quad = new QuadraticEquation(a, b, c);
					queue.add(quad);
					String text  = "Total No of Equations Created:" + quad.getNoofObjects();
					label_equation_created.setText(text);;
					if(queue.isFull()){
						queue_full_label.setForeground(Color.green);
						
					}
					else{
						queue_full_label.setForeground(Color.black);

					}

					}catch(NumberFormatException ae){
						System.out.println(t1.getText());
						System.out.println(t2.getText());
						System.out.println(t3.getText());

					}
					
				}
				Thread.sleep(150);
				
			}
		}
		catch(InterruptedException e){}
	}
	


	


// Write the code for this class
	
	

}// End of CreateThread class


class ComputeThread extends Thread
{
	private QuadraticEquationQueue queue;
	private JLabel[] 	displayLabels = new JLabel[10];
	private JLabel total_equation;
	private JLabel		countLabel1;
	private JLabel		countLabel2;
	private JLabel		countLabel3;
	private	static  int	totalCount=0;
	private static  int	count1=0;
	private static  int	count2=0;
	private static  int	count3=0;
	
	private	int	threadNo;
	private	boolean	suspendFlag;

	private JLabel	queueEmpty;

	public ComputeThread(QuadraticEquationQueue queue, JLabel[] displayLabels,
			JLabel total_equation, JLabel countLabel1, JLabel countLabel2,
			JLabel countLabel3, int threadNo, JLabel queueEmpty) {
		this.queue = queue;
		this.displayLabels = displayLabels;
		this.total_equation = total_equation;
		this.countLabel1 = countLabel1;
		this.countLabel2 = countLabel2;
		this.countLabel3 = countLabel3;
		this.threadNo = threadNo;
		this.queueEmpty = queueEmpty;
	}
	public void mysuspend()
	{
	suspendFlag = true;
	}
	public synchronized void myresume()
	{
	suspendFlag = false;
	notify();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			while(true){
				synchronized (this) {
					while(suspendFlag) wait();
					QuadraticEquation quad = queue.remove();
					if(queue.isEmpty()){
						queueEmpty.setForeground(Color.green);
					}
					else{
						queueEmpty.setForeground(Color.black);

					}
					boolean flag = quad.getisSolved();
					if(flag) continue;
					int a	= totalCount % 10;
					quad.computeRoots();
					displayLabels[a].setText(quad.toString());
					totalCount++;
					total_equation.setText("Total No of Equations Solved :"+totalCount);
					if (threadNo == 1)
					{
						count1++;
						countLabel1.setText("By Thread1:"+count1);
					}
					if (threadNo == 2)
					{
						count2++;
						countLabel2.setText("By Thread2:"+count2);
					}
					if (threadNo == 3)
					{
						count3++;
						countLabel3.setText("By Thread3:"+count3);
					}
					Thread.sleep(150);



					
				}
			}
		}
		catch (Exception e){}
	}


	


// Write the code for this class

}// End of CreateThread class