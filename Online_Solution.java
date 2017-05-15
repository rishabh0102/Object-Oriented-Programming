import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

// The following class Encapsulates a Polynomial of Given degree 
class Polynomial
{
	private	ArrayList<Integer>	coefficients;			// Coefficients of Polynomial
	private	int					degree;					// Degree of Polynomial
	private	Random				r;						// A Random Number Generator Variable
	private	double				rootValue;				// Root Value of the Polynomial
	private	boolean				isRootValueComputed; 	// Flag To Check if Root has been Compued or Not
	// Constructor Method
	Polynomial(int degree)
	{
		coefficients	=  new ArrayList<Integer>(degree);
		this.degree = degree;
		r	= new Random();
		isRootValueComputed = false;
		for(int i =0 ; i<=degree; i++)
		{
			int x =0;
			while(x == 0) x = r.nextInt(10);
			x = x * (int) Math.pow(-1,x);
			coefficients.add(x);
		}
	}
	// Accessor Methods
	public	ArrayList<Integer> 	getCoefficients() 			{ return coefficients;}
	public	int					getDegree()					{ return degree; }
	public	double				getRootValue()				{ return rootValue;}
	public	boolean				getIsRootValueComputed() 	{ return isRootValueComputed; }
	
	// Method to Compute the Value of a Polynomial at 'x' 
	public	double		computeValueAt(double x)
	{
		double value = 0;
		for(int i =0 ; i<=degree; i++)
			value = value + coefficients.get(i) * Math.pow(x,i);
		return value;
	}
	// ToString() Method
	public	String 	toString()
	{
		String Fx = "F(x) = ";
		for(int i =0 ; i<=degree; i++)
		{
			if(i < degree)
				Fx = Fx + coefficients.get(i) + "*" + "x^" + i + " + ";
			else
				Fx = Fx + coefficients.get(i) + "*" + "x^" + i ;
		}
		return Fx;
	}
	// Mutator Method for Updating root value
	public	void	setRootValue(double value)
	{
		this.rootValue = value;
		setIsRootValueComputed(true);
	}
	// Mutator Method for Updating root value flag 
	public	void	setIsRootValueComputed(boolean value)
	{
		isRootValueComputed = value;
	}
}// End of class Polynomial

// The following class Stores a List of Polynomials 
class PolynomialList
{  
	private	ArrayList<Polynomial>	 polynomialList;			//	Polynomial List 

	private	Polynomial				currentPolynomial;			//	This field represents the currently selected polynomial from the Polynomial List 

	// Constructor Method
	PolynomialList()	{ polynomialList = new ArrayList<Polynomial>(); currentPolynomial = null;}
	
	// Accessor Method for Polynomial List 
	public	ArrayList<Polynomial> getPolynomialList()	{  return   polynomialList;  }
	
	// Accessor Method For currentPolynomial
	public	Polynomial		getCurrentPolynomial()			{	return 	currentPolynomial; 	}

	// Mutator Method For currentPolynomial
	public	void		setCurrentPolynomial(Polynomial p)	{	currentPolynomial = p; 		}
	
	// Accessor Method to Retrieve a Polynomial From a Given Index
	public	Polynomial	getPolynomialAtIndex(int index)
	{
		if (index < 0 || index >= polynomialList.size())
		{
			System.out.println(" Out of Bounds ");
			System.out.println(" Current Size = " + polynomialList.size() + "Passed Index : "+ index);
			return null;
		}
		else
			return polynomialList.get(index);
	}
	// Method to Add a Polynomial in Polynomial List 
	public	void	addPolynomial(Polynomial p)
	{
		polynomialList.add(p);
	}
	// Method to Initialize Polynomial List 
	public	void	intializePolynomialList(int no)
	{
		/* This Method Randomly Creates 10 Polynomials with 3<=degree<=5 and adds them in Polynomial List */
		for(int i =0 ; i< no; i++)
		{
		     int degree = 0;
		     Random r = new Random();
		     while ( degree <= 2 || degree >= 6)
			degree = r.nextInt(10);
		     Polynomial P = new Polynomial(degree);
		     polynomialList.add(P);
		}
	}
	// Method to display the Created Polynomials on System.out
	public	void	displayPolynomialList()
	{
		for(int i =0 ; i< polynomialList.size() ; i++)
		{
			System.out.print(" Polynomial No : " + (i+1) +"\t");
			System.out.print(" Degree :  " + "\t" + polynomialList.get(i).getDegree());
			System.out.print("\t" +polynomialList.get(i));
			System.out.println();
		}
	}
	
}
// BisectionThread class
class BisectionThread extends Thread
{ 
	private	PolynomialList	polyList;				// Polynomial List
	private JTextField		rootValueTextField;		// Root Value Text Field
	private JTextField		computeThreadTextField; // Compute Thread Text Field
	private JTextArea		bisectionArea;			// Bisection TextArea For Bisection Method
	private JButton			resetButton;			// Reset Button Update
	private boolean			suspendFlag;			// Flag to Suspend and Resume Thread
	// Constructor Method
	BisectionThread(PolynomialList p, JTextField root, JTextField computeT, JTextArea bisectionArea, JButton resetB)
	{
		polyList = p; rootValueTextField = root; computeThreadTextField = computeT;
		this.bisectionArea = bisectionArea; resetButton = resetB;
		suspendFlag = false;
	}
	// Methods to Suspend and Resume Threads
	public boolean	isSuspended()			{ return suspendFlag;	}
	public void mysuspend()					{ suspendFlag = true;}
	public synchronized void myresume() 	{ suspendFlag = false; notify(); }
	
	// Method For Initial Guess Values 
	private double[] guessInitialValuesForPolynomial(Polynomial p)
	{
		double[] values = new double[2];
		Scanner sc = new Scanner(System.in);
		System.out.println("For Selected Polynomial "+ p);
		while(true)
		{
			System.out.println("Enter First Guess Value a:");
			double a = sc.nextDouble();
			System.out.println("Enter First Guess Value a:");
			double b = sc.nextDouble();
			double fa = p.computeValueAt(a);
			double fb = p.computeValueAt(b);
			if( fa * fb < 0) { values[0] = a; values[1] = b; break;}
			else
			{
				System.out.println(" Wrong Guesss Values ");
				System.out.println(" F(a) = "+ fa + " F(b) = "+ fb);
				System.out.println(" Enter Another Guess Values");
			}
		}
		return values;
	}
	
	// Run Method
	public void run()
	{
		bisectionArea.append("\n" + "Bisection Thread Started" +"\n");
		int equationKount = 0;
		while(equationKount < 3)
		{
			synchronized(this)
			{
				while(suspendFlag) 
				{
					try
					{
						this.wait();
					}
					catch(Exception e) {}
				}
			} // End of Synchronized Block
			synchronized(polyList)
			{
				while(polyList.getCurrentPolynomial() == null)
				{
					bisectionArea.append("\n" + "Bisection Thread Waiting Polynomial Equation Not Generated");
					try 
					{ 
						polyList.wait(); 
					} 
					catch(Exception e) {}
				}
				
				bisectionArea.append("\n" + "Bisection Thread Started For Polynomial Equation No: " + (equationKount+1)+"\n");
				
				Polynomial p = polyList.getCurrentPolynomial();
				
				double[] values = guessInitialValuesForPolynomial(p);
				
				double a = values[0]; double b = values[1]; 
				
				System.out.println("a=" + a + "b=" + b + "Bisection Method" +" Equaltion No:" + (equationKount+1));
								
				bisectionArea.append("Iteration"+"\t\t"+"a="+"\t\t"+"b="+"\t\t"+"c="+"\t\t"+"F_At_a"+"\t\t"+"F_At_b"+"\t\t"+"F_At_c"+"\n");
				
				int iterations =0;
				double c = 0.0;
				for (iterations =0; iterations < 20 ; iterations++)
				{
					if(p.getIsRootValueComputed())
					{
						mysuspend();
						break;
					}
					c = (a + b) / 2;
					double fa = p.computeValueAt(a);
					double fb = p.computeValueAt(b);
					double fc = p.computeValueAt(c);
					int itr = iterations + 1;
					//System.out.println("Iteration No : " + (iterations + 1) +" a = " + a + " b =" + b +" c = " + c +" F(c)=" + fc);
					bisectionArea.append(itr+"\t\t"+(float) a+"\t\t"+(float) b+"\t\t"+(float) c+"\t\t"+(float) fa+"\t\t"+(float) fb+"\t\t"+(float) fc+"\n");
					if( Math.abs(fc) <= 0.0001 && Math.abs(b-a) <= 0.0001) 
					{
						//System.out.println(" Root Value = "+ c);
						rootValueTextField.setText(""+c);
						computeThreadTextField.setText("Bisection Thread");
						p.setRootValue(c);
						break;
					}
					else
					{
						if ( fa * fc > 0)
							a = c;
						else
							b = c;
					}
					try { polyList.notifyAll(); polyList.wait(); polyList.notifyAll();} catch(Exception e) {}
				} // End of 20 Iterations
				equationKount++;
				if( iterations == 20)
				{
						bisectionArea.append("\n" + "Bisection Thread Does Not Converge in 20 Iterations" + "\n");
						bisectionArea.append("\n" + "Bisection Thread Suspending Itself" + "\n");
				}
				else if(suspendFlag)
				{
						bisectionArea.append("\n" + "Root Value Computed By Regula Falsi Thread" + "\n");
						bisectionArea.append("\n" + "Bisection Thread Suspending Itself" + "\n");
				}
				else
				{
						bisectionArea.append("\n" + "Bisection Thread Computed Root Value:" + p.getRootValue()+"\n");
						bisectionArea.append("\n" + "Number of Iterations Taken: "+ (iterations+1));
						bisectionArea.append("\n" + "Bisection Thread Suspending ItSelf" + "\n");
				}
				resetButton.setEnabled(true);
				polyList.notifyAll();
				mysuspend();
			}// End of synchronized block
		}// End of while loop
	} // End of run() Method
		
} // End of Bisection Method Thread Class

// RegulaFalsi Thread class	
class RegulaFalsiThread extends Thread
{
	private	PolynomialList	polyList;					// Polynomial List
	private JTextField		rootValueTextField;			// Root Value Text Field 
	private JTextField		computeThreadTextField;		// ComputeThread Text Field
	private JTextArea		regulaFalsiArea;			// Dedicated Text Area 
	private JButton			resetButton;				// Reset Button Update
	private boolean			suspendFlag;				// Flag to suspend and resume thread
	// Constructor Method
	RegulaFalsiThread(PolynomialList p, JTextField root, JTextField computeT, JTextArea regulaFalsiArea, JButton resetB)
	{
		polyList = p; rootValueTextField = root; computeThreadTextField = computeT;
		this.regulaFalsiArea = regulaFalsiArea; resetButton = resetB;
		suspendFlag = false;
	}
	// Methods to suspend and resume Thread	
	public boolean	isSuspended()			{ return suspendFlag;	}
	public void 	mysuspend()				{ suspendFlag = true;}
	public synchronized void myresume() 	{ suspendFlag = false; this.notify(); }
	
	// Method to read initial guess values
	private double[] guessInitialValuesForPolynomial(Polynomial p)
	{
		double[] values = new double[2];
		Scanner sc = new Scanner(System.in);
		System.out.println("For Selected Polynomial "+ p);
		while(true)
		{
			System.out.println("Enter First Guess Value a:");
			double a = sc.nextDouble();
			System.out.println("Enter First Guess Value a:");
			double b = sc.nextDouble();
			double fa = p.computeValueAt(a);
			double fb = p.computeValueAt(b);
			if( fa * fb < 0) { values[0] = a; values[1] = b; break;}
			else
			{
				System.out.println(" Wrong Guesss Values ");
				System.out.println(" F(a) = "+ fa + " F(b) = "+ fb);
				System.out.println(" Enter Another Guess Values");
			}
		}
		return values;
	}	// End of Method
	
	// run() Method
	public void run()
	{
		regulaFalsiArea.append("\n" + "Regula Falsi Thread Started" +"\n");
		int equationKount = 0;
		while(equationKount < 3)
		{
			synchronized(this)
			{
				while(suspendFlag) 
				{
					try
					{
						this.wait();
					}
					catch(Exception e) {}
				}
			}// End of Synchronized Block
			synchronized(polyList)
			{
				while(polyList.getCurrentPolynomial() == null)
				{
					regulaFalsiArea.append("\n" + "Regula Falsi Thread Waiting Polynomial Equation Not Generated");
					try 
					{ 
						polyList.wait(); 
					} 
					catch(Exception e) {}
				}
				
				regulaFalsiArea.append("\n" + "Regula Falsi Thread Started For Polynomial Equation No: " + (equationKount+1)+"\n");
				
				Polynomial p = polyList.getCurrentPolynomial();
				
				double[] values = guessInitialValuesForPolynomial(p);
				
				double a = values[0]; double b = values[1]; 
				
				System.out.println("a=" + a + "b=" + b + "Regula Falsi Method" +" Equaltion No:" + (equationKount+1));
				
				regulaFalsiArea.append("Iteration"+"\t\t"+"a="+"\t\t"+"b="+"\t\t"+"c="+"\t\t"+"F_At_a"+"\t\t"+"F_At_b"+"\t\t"+"F_At_c"+"\n");
				
				int iterations =0;
				double c 	= 	0.0;
				double fa 	=	p.computeValueAt(a);
				double fb 	=	p.computeValueAt(b);
				double fc	=	0.0;
				for (iterations =0; iterations < 20 ; iterations++)
				{
					if(p.getIsRootValueComputed())
					{
						mysuspend();
						break;
					}
					c = (a * fb - b * fa)/ (fb - fa);
					fc = p.computeValueAt(c);
					int itr = iterations + 1;
					regulaFalsiArea.append(itr+"\t\t"+(float) a+"\t\t"+(float) b+"\t\t"+(float) c+"\t\t"+(float) fa+"\t\t"+(float) fb+"\t\t"+(float) fc+"\n");
					if( Math.abs(fc) <= 0.0001 ) 
					{
						rootValueTextField.setText(""+c);
						computeThreadTextField.setText("Regula Falsi Thread");
						p.setRootValue(c);
						break;
					}
					else
					{
						if ( fb * fc < 0)
							a = c;
						else
							b = c;
					}
					try { polyList.notifyAll(); polyList.wait(); polyList.notifyAll();} catch(Exception e) {}
				} // End of 20 Interations
				equationKount++;
				if( iterations == 20)
				{
						regulaFalsiArea.append("\n" + "Regula Falsi Thread Does Not Converge in 20 Iterations" + "\n");
						regulaFalsiArea.append("\n" + "Regula Falsi Thread Suspending Itself" + "\n");
				}
				else if(suspendFlag)
				{
						regulaFalsiArea.append("\n" + "Root Value Computed By Bisection Thread" + "\n");
						regulaFalsiArea.append("\n" + "Regula Falsi Thread Suspending Itself" + "\n");
				}
				else
				{
						regulaFalsiArea.append("\n" + "Regula Falsi Thread Computed Root Value:" + p.getRootValue()+"\n");
						regulaFalsiArea.append("\n" + "Number of Iterations Taken: "+ (iterations+1));
						regulaFalsiArea.append("\n" + "Regula Falsi Thread Suspending ItSelf" + "\n");
				}
				resetButton.setEnabled(true);
				//polyList.notifyAll();
				mysuspend();
			}// End of Synchronized Block
		}// End of Loop
	}// End of run Method
} // End of RegulaFalsi Thread class

class Test
{
	// Main Frame Window
	public static	 JFrame	mainFrame 			= 	new JFrame("Polynomial Frame");
	
	// Generate Polynomial Button
	public static JButton	generatePolynomialButton 	= 	new JButton("Select Polynomial");  	// Button to Generate Polynomial
	
	// Label to Display Polynomial
	public static JLabel	polynomialLabel 		= 	new JLabel("Polynomial = ");
	public static JLabel	polynomialEquationLabel	=	new JLabel();
	
	// Label For Computed Root
	public static JLabel	rootValueLabel		=	new JLabel("Root Value: ");
	
	// Text Field to Display the Value of Computed Root
	public static JTextField	rootValueTextField		=	new JTextField(15);
	
	// Label to Display The Name of Thread Which has Computed the Root Value
	public static JLabel	computeThreadLabel		=	new JLabel("Computed By Thread");
	
	// Text Field to Display The Name of Thread Which has Computed the Root Value
	public static JTextField	computeThreadTextField	=	new JTextField(15);
	
	
	// Button to Start the Threads
	public static JButton	startRootThreads		=  	new JButton("Start Root Threads");

	// Button to Reser For Next Iteration
	public static JButton	resetButton			=  	new JButton("Reset For Next Polynomial");
	
	// Bisection Method Thread Text Area
	public static JTextArea  bisectionTextArea		=	new JTextArea(20,100);
	
	// Regula Falsi Method Thread Text Area
	public static JTextArea  regulaFalsiTextArea	=	new JTextArea(20,100);

	// Bisection Scroll Bar
	public static JScrollPane bisectionScroller		=	new JScrollPane(bisectionTextArea);
	
	// Regula Falsi Scroll Bar
	public static JScrollPane regulaFalsiScroller	=	new JScrollPane(regulaFalsiTextArea);

	// Panels For Various Components
	public static JPanel	panelLine1		=	new	JPanel();
	public static JPanel	panelLine2		=	new	JPanel();
	public static JPanel	panelLine3		=	new	JPanel();
	public static JPanel	panelLine4		=	new	JPanel();
	public static JPanel	panelLine5		=	new	JPanel();
	public static JPanel	panelLine6		=	new	JPanel();
	public static JPanel	panelLine7		=	new	JPanel();

	// Overall Main Panel
	public static JPanel	overallMainPanel	=	new	JPanel();
	
	// Shareable Polynomial List
	public static PolynomialList pList = new PolynomialList(); 
	
	// Equation Kount Class Variable
	public static int				equationKount = 0;
	
	// Flag Variables to Check Whether Threads are Started or Not
	public static boolean bisectionThreadStarted		=	false;
	public static boolean regulaFalsiThreadStarted		=	false;

	// Method to display frame 
	public static void displayFrame()
	{
		mainFrame.setLayout(new BorderLayout());
		
		
		overallMainPanel.setLayout(new BoxLayout(overallMainPanel,BoxLayout.Y_AXIS));

		panelLine1.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelLine2.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelLine3.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelLine4.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelLine5.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelLine6.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelLine7.setAlignmentX(Component.CENTER_ALIGNMENT);

		panelLine1.add(generatePolynomialButton);
		
		panelLine2.add(polynomialLabel); panelLine2.add(polynomialEquationLabel); 	

		panelLine3.add(rootValueLabel); panelLine3.add(rootValueTextField);
	
		panelLine4.add(computeThreadLabel); panelLine4.add(computeThreadTextField);
	
		startRootThreads.setEnabled(false);
		resetButton.setEnabled(false);
		panelLine5.add(startRootThreads); panelLine5.add(resetButton);
		
		bisectionTextArea.append("\t Bisection Thread");
		panelLine6.add(bisectionScroller);
		
		regulaFalsiTextArea.append("\t Regula Falsi Thread");
		panelLine7.add(regulaFalsiScroller);

		overallMainPanel.add(panelLine1); overallMainPanel.add(panelLine2); overallMainPanel.add(panelLine3);
		overallMainPanel.add(panelLine4); overallMainPanel.add(panelLine5); overallMainPanel.add(panelLine6);
		overallMainPanel.add(panelLine7); 
	
		mainFrame.add(overallMainPanel,BorderLayout.CENTER);
		mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);	
	}
	
	// Driver Main Method
	public static void main(String args[])
	{
		pList.intializePolynomialList(10); 	// Initialize Polynomial List with 10 Polynomials
		//pList.displayPolynomialList();		// Display the Generetaed Polynomials 
		
		displayFrame();						// Display Frame Window
		
		final BisectionThread  bisectionThread = new BisectionThread (pList,rootValueTextField,computeThreadTextField,bisectionTextArea,resetButton);
		bisectionThread.start();
		bisectionThreadStarted = true;
		
		final RegulaFalsiThread  regulaFalsiThread = new RegulaFalsiThread (pList,rootValueTextField,computeThreadTextField,regulaFalsiTextArea,resetButton);
		regulaFalsiThread.start();
		regulaFalsiThreadStarted = true;
		
		generatePolynomialButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if (equationKount == pList.getPolynomialList().size()) { System.exit(0); return; }
				else
				{
					String str = pList.getPolynomialList().get(equationKount).toString();
					polynomialEquationLabel.setText(str);
					generatePolynomialButton.setEnabled(false);
					startRootThreads.setEnabled(true);
					pList.setCurrentPolynomial(pList.getPolynomialList().get(equationKount));
					equationKount=equationKount + 1;
				}
			}
		});
		startRootThreads.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(bisectionThread.getState() == Thread.State.WAITING || regulaFalsiThread.getState() == Thread.State.WAITING) 
				{
					synchronized (pList) { pList.notifyAll(); } 
				}
				bisectionThread.myresume();
				regulaFalsiThread.myresume();
				startRootThreads.setEnabled(false);
			}
		});
		resetButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				polynomialEquationLabel.setText("");
				rootValueTextField.setText("");
				computeThreadTextField.setText("");
				pList.setCurrentPolynomial(null);
				resetButton.setEnabled(false);
				generatePolynomialButton.setEnabled(true);
			}
		});
		
		
	}
}
	