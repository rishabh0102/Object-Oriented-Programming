import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Question
{
	private	String		question;
	private	String[]	optionSequence		=	new	String[4];
	private	int		correctSequence[]	=	new	int[4];
	Question(String question,String[]	optionSequence,	int[]	sequence)
	{
		this.question		=	question;
		this.optionSequence	=	optionSequence;
		this.correctSequence	=	sequence;
	}
	public	String		getQuestion()		{	return	question;		}
	public	int[]		getCorrectSequence()	{	return	correctSequence;	}
	public	String[]	getOptionSequence()	{	return  optionSequence;		}
}// End of class Question

class Answer	implements	Comparable<Answer>
{
	private	int	answerSequence[]	=	new	int[4];
	private	int	playerid;
	private	long	timetaken;
	
	public	int[]	getAnswerSequence()	{	return 	answerSequence;		}
	public	int	getPlayerid()		{	return	playerid;		}
	public	long	getTimetaken()		{	return	timetaken;		}

	public	void	setAnswerSequence(int[]	answerSequence)		{	this.answerSequence	=	answerSequence;		}
	public	void	setPlayerid(int		id)			{	this.playerid		=	id;			}
	public	void	setTimetaken(long	timeTaken)		{	this.timetaken		=	timeTaken;		}

	public	int	compareTo(Answer other)	
	{	
			if (this.timetaken	>	other.timetaken)	return 	1;
			if (this.timetaken	<	other.timetaken)	return 	-1;
			return 0;	

	}
	public	String	toString()		
	{
		String s1 = "Player Id: "+playerid+"\t";
		String s2 = "Answered Sequence : [ "+ answerSequence[0]+"  "+answerSequence[1]+"  "+answerSequence[2]+"  "+answerSequence[3]+" ] "+"\t";
		String s3 = "Time Taken: "+timetaken;
		return s1+s2+s3;
	}
}// End of class Answer

class AnswerBoard
{
	private		ArrayList<Answer>	answerList		=	new	ArrayList<Answer>();
	private		Question		currentQuestion;
	private		long			initialTime;	
	private		boolean			isLocked;
	
	AnswerBoard()
	{
		answerList.add(null);answerList.add(null);answerList.add(null);answerList.add(null);answerList.add(null);
		answerList.add(null);answerList.add(null);answerList.add(null);answerList.add(null);answerList.add(null);
		isLocked	=	true;
	}
	
	//	Accessor Method(s)
	public	ArrayList<Answer>				getAnswerList()		{	return	answerList;		}
	public	synchronized		boolean			getIsLocked()		{	return	isLocked;		}
	public	long						getInitialTime()	{	return  initialTime;		}
	public	Question					getCurrentQuestion()	{	return	currentQuestion;	}

	public	void	lockAnswerBoard()	{	isLocked	=	true;	System.out.println("AnswerBoard Locked");		}
	public	void	unlockAnswerBoard()	{	isLocked	=	false;	System.out.println("AnswerBoard UnLocked");		}
	
	public	void	setStarttime()		{	initialTime	=	System.nanoTime();	}
	
	public	void	postQuestion(Question	q)	
	{	
		currentQuestion	=	q;
		unlockAnswerBoard();
		setStarttime();			//	or	initialTime	=	System.nanoTime();
	}
	public	synchronized	boolean		addAnswer(Answer	ans,	int	place)	
	{
			if(!isLocked)
			{
				ans.setTimetaken(System.nanoTime()-initialTime);
				answerList.set(place,ans);return true;
			}
			else	{	System.out.println("Answer Refused");return false;	}
	}
	public	void	initializeAnswerList()
	{
		answerList.set(0,null);answerList.set(1,null);answerList.set(2,null);answerList.set(3,null);answerList.set(4,null);answerList.set(5,null);
		answerList.set(6,null);answerList.set(7,null);answerList.set(8,null);answerList.set(9,null);
		initialTime=0;			//	or	initialTime	=	System.nanoTime();
	}
	
}

class Player	extends		Thread
{
	private		int		playerId;
	private		JButton		button;
	private		Answer		answer;
	private		AnswerBoard	answerboard;
	private		boolean		suspendFlag;
	private		Random		random;
	
	Player(int	id,	JButton		btn,	Answer	ans,	AnswerBoard	answerboard)
	{
		button 			= 	btn;
		playerId		=	id;
		answer			=	ans;
		this.answerboard	=	answerboard;
		suspendFlag		=	true;
		random			=	new	Random();
		
	}
	
	public	void			mysuspend()	{	suspendFlag = true;	}
	public	synchronized	void	myresume()	
	{	
		//System.out.println("Player "+playerId+" Resuming");
		suspendFlag = false;
		notify();
	}
	public	boolean			isSuspended()					{	return	suspendFlag;		}
	public	void			chgbtncolor(int value)	
	{
		if(value	==	1)	button.setBackground(Color.GRAY);
		if(value	==	2)	button.setBackground(Color.BLUE);
		if(value	==	3)	button.setBackground(Color.RED);
		if(value	==	4)	button.setBackground(Color.GREEN);
		if(value	==	5)	button.setBackground(Color.CYAN);
	}		
	public	void	run()
	{
		try
		{
			synchronized(this)
			{
				for(int i =0;	i<10;	i++)	//while(true)
				{
					
					while(suspendFlag)	
					{
						//System.out.println(playerId+" Waiting ");	
						wait();
					}			
					System.out.println(playerId+" Going for Game Number:"+i);
					chgbtncolor(1);
					int[]	answers	=	new	int[4];
					int a=0,b=0,c=0,d=0;
					while(a == 0)					a = random.nextInt(5);
					Thread.sleep(random.nextInt(10)*250+10);
					while(b == 0 || b == a)				b = random.nextInt(5);
					Thread.sleep(random.nextInt(10)*250+10);
					while(c == 0 || b == c	|| a == c)		c = random.nextInt(5);
					Thread.sleep(random.nextInt(10)*250+10);
					while(d == 0 || a == d	|| b == d || c == d )	d = random.nextInt(5);
					Thread.sleep(random.nextInt(10)*250+10);
					answers[0] = a;answers[1] = b;answers[2] = c;answers[3] = d;
					answer.setAnswerSequence(answers);
					answer.setPlayerid(playerId);
					if(answerboard.addAnswer(answer,playerId-1))
					{
							System.out.print(playerId+" Posted For Game No: "+ i);
							chgbtncolor(2);
					}
					else
					{
							System.out.println(playerId+" Unable to Answer For Game Number:"+i);
							chgbtncolor(5);mysuspend();continue;
					}
					mysuspend();
				}
				//mysuspend();		
			}
		}catch(InterruptedException e)			{		}
	}
}

class TimerThread	extends		Thread
{
	private		JTextField		textF;
	private		boolean			suspendFlag;
	public		boolean			timeOver;

	public		void			mysuspend()	{	suspendFlag = true;	}
	public		synchronized	void	myresume()	
	{	
		System.out.println("Timer Resuming");
		suspendFlag = false;
		notify();	
	}
	
	TimerThread(JTextField	txt)
	{
		textF		=	txt;
		suspendFlag	=	true;
		timeOver	=	false;
	}
	public	void		setTimeOver(boolean v) 				{	timeOver=v;		}
	public	synchronized	boolean		getTimeOver()			{	return	timeOver;	}
	public	void	run()
	{
		try
		{
			while(true)
			{
				synchronized(this)	
				{	
					while(suspendFlag)	{ 	System.out.println("Timer has been Suspended");		wait();		}
					for(int k = 10; k >=0 ; k--)
					{ 
						textF.setText(new Integer(k).toString());
						Thread.sleep(1000);
					}
					mysuspend();	
					setTimeOver(true); 
				}
				
			}
		}catch(InterruptedException e)			{		}
	}	
					
}
				
class MasterController	extends	Thread
{
	private	ArrayList<Player>	playerList;
	private	AnswerBoard		answerBoard;
	private	JTextField		timerTextField;
	private	JTextArea		resultTextArea;
	private	JTextField		oT1;
	private	JTextField		oT2;
	private	JTextField		oT3;
	private	JTextField		oT4;

	private	JTextField		cT1;
	private	JTextField		cT2;
	private	JTextField		cT3;
	private	JTextField		cT4;
	private	JTextField		winnerPlayer;
	private JTextField		timeTaken;		

	private	TimerThread		timer;
	private	Question		currentQuestion;
	private	JButton			resetB;
	private	boolean			suspendFlag;
	
	public	void			mysuspend()	{	suspendFlag = true;	}
	public	synchronized	void	myresume()	
	{	
		//System.out.println("Master Resuming");
		suspendFlag = false;
		notify();	
	}

	MasterController(ArrayList<Player> playerList,AnswerBoard answerBoard,JTextField timerTextField,JTextArea resultTextArea,JTextField oT1,JTextField oT2,JTextField oT3,JTextField oT4, JTextField ct1, JTextField ct2, JTextField ct3, JTextField ct4,JTextField t5,JTextField t6,JButton b)
	{
	this.playerList		=	playerList;
	this.answerBoard	=	answerBoard;
	this.timerTextField	=	timerTextField;
	this.resultTextArea	=	resultTextArea;
	this.oT1		=	oT1;
	this.oT2		=	oT2;
	this.oT3		=	oT3;
	this.oT4		=	oT4;
	
	cT1			=	ct1;
	cT2			=	ct2;
	cT3			=	ct3;
	cT4			=	ct4;
	resetB			=	b;
	winnerPlayer		=	t5;
	timeTaken		=	t6;
	suspendFlag		=	true;
	timer			=	new	TimerThread(timerTextField);
	timer.start();
	}	
	public	void	run()
	{
		try
		{
			
			synchronized(this)	
			{	
				for(int k =0;	k<10;	k++)	
				{
					while(suspendFlag)		
					{	
						System.out.println("Master Waiting");				
						wait();			
					}
					System.out.println("Master Going For Game "+k);
					// 	Resuming Timer	Task-1
					timer.myresume();
					
					// Displaying Options	Task-2
					currentQuestion			=	answerBoard.getCurrentQuestion();
					String[]	questionSeq	=	currentQuestion.getOptionSequence();
					oT1.setText(questionSeq[0]);
					oT2.setText(questionSeq[1]);
					oT3.setText(questionSeq[2]);
					oT4.setText(questionSeq[3]);
					
					

					// Resuming Players	Task-3
					for(int i = 0;	i<10;	i++)	playerList.get(i).myresume();
					
					//System.out.println("Timer Started");
					
					// Wait for timer to stop	Task-4
					
					while(timer.getTimeOver() ==	false)		{/*Just do Nothing*/}
					
					timer.setTimeOver(false);	// Reset Timer Task-5
					
					answerBoard.lockAnswerBoard();	// Lock Answer Board	Task-6
					
					// Changing the color of Player buttons	Task-7
					
					ArrayList<Answer>	answerList	=	answerBoard.getAnswerList();
					
					int[]	seq=currentQuestion.getCorrectSequence();	// Reading the correct sequence of the answer
						
					for(int i = 0;	i<10;	i++)
					{
						if(answerList.get(i) == null)	{	playerList.get(i).chgbtncolor(5);	continue;	}
						Answer	ans	=	answerList.get(i);
						int[]	x1	=	ans.getAnswerSequence();
						if(seq[0] == x1[0] && seq[1] == x1[1] && seq[2] == x1[2] && seq[3] == x1[3])	playerList.get(i).chgbtncolor(4);	
						else			playerList.get(i).chgbtncolor(3);
					}
					

					// Displaying the Correct Answer Sequence	Task-7
					
 					
					cT1.setText(new Integer(seq[0]).toString());
					cT2.setText(new Integer(seq[1]).toString());
					cT3.setText(new Integer(seq[2]).toString());
					cT4.setText(new Integer(seq[3]).toString());
					
					// Displaying the Results 	Task-8
					for(int i = 0;	i<10;	i++)
					{
						if(answerList.get(i) == null)	
						{
							String str	=	"Player Id: "+	i +" Has Not Answered";
							resultTextArea.append(str+"\n");
							continue;
						}
						resultTextArea.append(answerList.get(i).toString()+"\n");
					}
					
					// Displaying the Winner	Task-9
					ArrayList<Answer>	correctList	=	new	ArrayList<Answer>();
					for(int i = 0;	i<10;	i++)
					{
						if(answerList.get(i) == null)	continue;
						Answer	ans	=	answerList.get(i);
						int[]	x1	=	ans.getAnswerSequence();
						if(seq[0] == x1[0] && seq[1] == x1[1] && seq[2] == x1[2] && seq[3] == x1[3])	correctList.add(ans);	
					}
					if(correctList.size()	!=	0)	
					{
						Collections.sort(correctList);
						Answer	ans		=	correctList.get(0);
						int 	id		=	ans.getPlayerid();
						long	timeT		=	ans.getTimetaken();
						winnerPlayer.setText(new Integer(id).toString());
						timeTaken.setText(new Long(timeT).toString());
					}
					
					resetB.setEnabled(true);	// Enabling Reset Button Task-10
					mysuspend();
				}
				// Suspending Matser
				//mysuspend();
			}
		}catch(InterruptedException e)			{		}
	}
}
								

class	Main
{
private	static	JFrame		frame			=	new	JFrame("Fastest Finger First");
private	static	JPanel		playerPanel		=	new	JPanel();
private	static	JButton		playerbtn1		=	new	JButton("P1");
private	static	JButton		playerbtn2		=	new	JButton("P2");
private	static	JButton		playerbtn3		=	new	JButton("P3");
private	static	JButton		playerbtn4		=	new	JButton("P4");
private	static	JButton		playerbtn5		=	new	JButton("P5");
private	static	JButton		playerbtn6		=	new	JButton("P6");
private	static	JButton		playerbtn7		=	new	JButton("P7");
private	static	JButton		playerbtn8		=	new	JButton("P8");
private	static	JButton		playerbtn9		=	new	JButton("P9");
private	static	JButton		playerbtn10		=	new	JButton("P10");
private	static	JPanel		mainPlayerPanel 	=	new	JPanel();
private	static	JPanel		p1			=	new	JPanel(new FlowLayout());
private	static	JButton		displayQ		=	new	JButton("Display Question");
private	static	JButton		startB			=	new	JButton("     Start      ");
private	static	JButton		resetB			=	new	JButton("     Reset      ");
private	static	JPanel		p2			=	new	JPanel();
private	static	JLabel		timerLabel		=	new	JLabel("Timer");
private	static	JPanel		p3			=	new	JPanel(new FlowLayout());
private	static	JLabel		questionLabel		=	new	JLabel("Question ");
private	static	JTextArea	questionArea		=	new	JTextArea(4,80);		
private	static	JTextField	timerTextField		=	new	JTextField(10);
private	static	JPanel		p4			=	new	JPanel(new	FlowLayout());
private	static	JLabel		optionLabel		=	new	JLabel("Options");
private	static	JPanel		p5			=	new	JPanel(new	FlowLayout());
private	static	JLabel		L1			=	new	JLabel("1. ");
private	static	JTextField	T1			=	new	JTextField(25);
private	static	JLabel		L2			=	new	JLabel("2. ");
private	static	JTextField	T2			=	new	JTextField(25);	
private	static	JPanel		p6			=	new	JPanel(new	FlowLayout());
private	static	JLabel		L3			=	new	JLabel("3. ");
private	static	JTextField	T3			=	new	JTextField(25);
private	static	JLabel		L4			=	new	JLabel("4. ");
private	static	JTextField	T4			=	new	JTextField(25);
private	static	JPanel		p7			=	new	JPanel(new	FlowLayout());
private	static	JLabel		correctSequence		=	new	JLabel("Correct Sequence");
private	static	JTextField	CT1			=	new	JTextField(2);
private	static	JTextField	CT2			=	new	JTextField(2);
private	static	JTextField	CT3			=	new	JTextField(2);
private	static	JTextField	CT4			=	new	JTextField(2);
private	static	JPanel		p8			=	new	JPanel(new FlowLayout());
private	static	JLabel		resultLabel		=	new	JLabel("Results");	
private	static	JPanel		p9			=	new	JPanel(new FlowLayout());
private	static	JTextArea	resultDisplayArea	=	new	JTextArea(15,80);
private	static	JPanel		p10			=	new	JPanel(new FlowLayout());
private	static	JLabel		winner			=	new	JLabel(" Winner	Details ");
private	static	JLabel		winnerPlayer		=	new	JLabel("Player ");
private	static	JTextField	winnerText		=	new	JTextField(10);
private	static	JLabel		timeTaken		=	new	JLabel("Time Taken ");
private	static	JTextField	timetakenText		=	new	JTextField(10);
private	static	JPanel		gamePanel		=	new	JPanel(new FlowLayout());
private	static	JLabel		gameLabel		=	new	JLabel("Game Number");
private	static	JTextField	gamenoText		=	new	JTextField(5);
private	static	JPanel		centralPanel		=	new	JPanel();

private	static	ArrayList<Question>	questionList	=	new	ArrayList<Question>();
private	static	AnswerBoard		answerBoard	=	new	AnswerBoard();	
private	static	int			gameKount	=	0;

//	Player Threads
private static	Player		fffP1;		
private static	Player		fffP2;		
private static	Player		fffP3;		
private static	Player		fffP4;		
private static	Player		fffP5;		
private static	Player		fffP6;		
private static	Player		fffP7;		
private static	Player		fffP8;		
private static	Player		fffP9;		
private static	Player		fffP10;	

//	Master Control Thread
private static	MasterController	master;	

	public	static	void	populateQuestionList()
	{
	
		String		Q1 	=	"Arrange The Following Parts of Body from Top to Bottom";
		String[]	chQ1	=	{"Stomach","Eyes","Foot","Head"};
		int[]		crSeq1	=	{4,2,1,3};
	
		String		Q2 	=	"Arrange The Following Events from Their Date of Early occurence to Late occurence";
		String[]	chQ2	=	{"Indian Freedom","Kargil War","World War - II","French Revolution"};
		int[]		crSeq2	=	{4,3,1,2};

		String		Q3 	=	"Arrange The Following Indian Cities From NORTH TO SOUTH";
		String[]	chQ3	=	{"Bangalore","Agra","Delhi","Kochin"};
		int[]		crSeq3	=	{3,2,1,4};

		String		Q4 	=	"Arrange The Following Indian Cities From SOUTH TO NORTH";
		String[]	chQ4	=	{"Bangalore","Agra","Delhi","Kochin"};
		int[]		crSeq4	=	{4,1,2,3};

		String		Q5 	=	"Arrange The Following Marriage Functions from early Happenning to Late Happening";
		String[]	chQ5	=	{"Ghud Chadi","Sagai","Mehndi","Barat"};
		int[]		crSeq5	=	{2,3,1,4};

		String		Q6 	=	"Arrange The Following Contries from WEST to EAST";
		String[]	chQ6	=	{"India","Pakistan","Iran","Israel"};
		int[]		crSeq6	=	{4,3,2,1};
	
		String		Q7 	=	"Arrange The Following Contries from EAST to WEST";
		String[]	chQ7	=	{"India","Iran","Pakistan","Israel"};
		int[]		crSeq7	=	{1,3,2,4};
	
		String		Q8 	=	"Arrange The Following Parts of Body from Bottom to Top";
		String[]	chQ8	=	{"Stomach","Eyes","Foot","Head"};
		int[]		crSeq8	=	{3,1,2,4};	
	
		String		Q9 	=	"Arrange The Following Distance Measurement Dimensions in Increasing Order of their value";
		String[]	chQ9	=	{"Centimeter","Kilometer","MilliMetet","Meter"};
		int[]		crSeq9	=	{3,1,4,2};
		
		String		Q10 	=	"Arrange The Following Distance Measurement Dimensions in Decreasing Order of their value";
		String[]	chQ10	=	{"Centimeter","Kilometer","MilliMetet","Meter"};
		int[]		crSeq10	=	{2,4,1,3};
	
		questionList.add(new	Question(Q1,chQ1,crSeq1));	questionList.add(new	Question(Q2,chQ2,crSeq2));	questionList.add(new	Question(Q3,chQ3,crSeq3));
		questionList.add(new	Question(Q4,chQ4,crSeq4));	questionList.add(new	Question(Q5,chQ5,crSeq5));	questionList.add(new	Question(Q6,chQ6,crSeq6));
		questionList.add(new	Question(Q7,chQ7,crSeq7));	questionList.add(new	Question(Q8,chQ8,crSeq8));	questionList.add(new	Question(Q9,chQ9,crSeq9));
		questionList.add(new	Question(Q10,chQ10,crSeq10));
	
	}
	public	static	void	createThreads()
	{
		fffP1	=	new	Player(	1,playerbtn1,new Answer(),answerBoard);
		fffP2	=	new	Player(	2,playerbtn2,new Answer(),answerBoard);
		fffP3	=	new	Player(	3,playerbtn3,new Answer(),answerBoard);
		fffP4	=	new	Player(	4,playerbtn4,new Answer(),answerBoard);
		fffP5	=	new	Player(	5,playerbtn5,new Answer(),answerBoard);
		fffP6	=	new	Player(	6,playerbtn6,new Answer(),answerBoard);
		fffP7	=	new	Player(	7,playerbtn7,new Answer(),answerBoard);
		fffP8	=	new	Player(	8,playerbtn8,new Answer(),answerBoard);
		fffP9	=	new	Player(	9,playerbtn9,new Answer(),answerBoard);
		fffP10	=	new	Player(	10,playerbtn10,new Answer(),answerBoard);
		ArrayList<Player>	playerList	=	new 	ArrayList<Player>();
		playerList.add(fffP1);playerList.add(fffP2);playerList.add(fffP3);playerList.add(fffP4);playerList.add(fffP5);playerList.add(fffP6);
		playerList.add(fffP7);playerList.add(fffP8);playerList.add(fffP9);playerList.add(fffP10);
		master	=	new	MasterController(playerList,answerBoard,timerTextField,resultDisplayArea,T1,T2,T3,T4,CT1,CT2,CT3,CT4,winnerText,timetakenText,resetB);
	}
	public	static	void	startThreads()
	{
		fffP1.start();fffP2.start();fffP3.start();fffP4.start();fffP5.start();fffP6.start();fffP7.start();fffP8.start();fffP9.start();fffP10.start();
		master.start();	
	}
	public	static	void	displayFrame()
	{
		startB.setEnabled(false);
		resetB.setEnabled(false);
		frame.setSize(1000,1000);
		frame.setLayout(new BorderLayout());
		playerPanel.setLayout(new	GridLayout(10,0));
		JLabel	label1		=	new	JLabel("Players");
		playerPanel.add(playerbtn1);playerPanel.add(playerbtn2);playerPanel.add(playerbtn3);playerPanel.add(playerbtn4);playerPanel.add(playerbtn5);
		playerPanel.add(playerbtn6);playerPanel.add(playerbtn7);playerPanel.add(playerbtn8);playerPanel.add(playerbtn9);playerPanel.add(playerbtn10);
		mainPlayerPanel.setLayout(new BoxLayout(mainPlayerPanel, BoxLayout.Y_AXIS));
		mainPlayerPanel.add(label1);
		mainPlayerPanel.add(playerPanel);
		p1.add(displayQ);
		p1.add(startB);
		p1.add(resetB);
		p2.setLayout(new FlowLayout());
		p2.add(timerLabel); p2.add(timerTextField);
		p3.add(questionLabel);p3.add(questionArea);
		p4.add(optionLabel);//,BorderLayout.CENTER);
		p5.add(L1);p5.add(T1);p5.add(L2);p5.add(T2);
		p6.add(L3);p6.add(T3);p6.add(L4);p6.add(T4);
		p7.add(correctSequence); p7.add(CT1);p7.add(CT2);p7.add(CT3);p7.add(CT4);
		p8.add(resultLabel);
		p9.add(resultDisplayArea);
		p10.add(winner);p10.add(winnerPlayer);p10.add(winnerText);p10.add(timeTaken);p10.add(timetakenText);
		gamePanel.add(gameLabel); gamePanel.add(gamenoText);
		centralPanel.setLayout(new BoxLayout(centralPanel,BoxLayout.Y_AXIS));
		centralPanel.add(p1);centralPanel.add(p2);centralPanel.add(p3);centralPanel.add(p4);centralPanel.add(p5);centralPanel.add(p6);centralPanel.add(p7);
		centralPanel.add(p8);centralPanel.add(p9);centralPanel.add(p10);centralPanel.add(gamePanel);
		frame.add(mainPlayerPanel,BorderLayout.WEST);
		frame.add(centralPanel,BorderLayout.CENTER);
		frame.setVisible(true);
	
	}
	public	static	void 	main(String args[])
	{
		
		displayFrame();
		populateQuestionList();
		createThreads();
		startThreads();
		
		
		displayQ.addActionListener(new ActionListener()
		{
			public	void	actionPerformed(ActionEvent ae)
			{
				
				if(gameKount	==	10)	System.exit(0);
				Question	currentQuestion 	=	questionList.get(gameKount);
				answerBoard.postQuestion(currentQuestion);
				String		ques	=	currentQuestion.getQuestion();
				questionArea.setText("");
				questionArea.setText(ques);
				timerTextField.setText("10");
				gameKount++;
				gamenoText.setText(new Integer(gameKount).toString());
				displayQ.setEnabled(false);
				startB.setEnabled(true);
			}
		});
		startB.addActionListener(new ActionListener()
		{
			public	void	actionPerformed(ActionEvent ae)
			{
				master.myresume();
				startB.setEnabled(false);
				//resetB.setEnabled(true);
			}
		});
		resetB.addActionListener(new ActionListener()
		{
			public	void	actionPerformed(ActionEvent ae)
			{
				playerbtn1.setBackground(null);
				playerbtn2.setBackground(null);
				playerbtn3.setBackground(null);
				playerbtn4.setBackground(null);
				playerbtn5.setBackground(null);
				playerbtn6.setBackground(null);
				playerbtn7.setBackground(null);
				playerbtn8.setBackground(null);
				playerbtn9.setBackground(null);
				playerbtn10.setBackground(null);
				questionArea.setText("");
				timerTextField.setText("");
				T1.setText("");T2.setText("");T3.setText("");T4.setText("");
				CT1.setText("");CT2.setText("");CT3.setText("");CT4.setText("");
				resultDisplayArea.setText("");
				winnerText.setText("");
				timetakenText.setText("");
				displayQ.setEnabled(true);
				resetB.setEnabled(false);
				answerBoard.initializeAnswerList();
				//answerBoard.lockAnswerBoard();
			}
		});
		
		
		
	}
}