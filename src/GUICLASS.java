import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
@SuppressWarnings("serial")
public class GUICLASS extends JPanel

{
	//member variables
	int runningCount=0;
	int choiceCount=0;
	int delay = 30;
	int lastButton=8;
	int timerCountRed=0;
	int timerCountYellow=0;
	int fallingRowRed=0;
	int fallingRowYellow=0;
	int danceCount=0;

	//create images
	private Timer timer;
	private final ImageIcon hankDance=new ImageIcon("danceHank.gif");
	private final ImageIcon johnDance=new ImageIcon("dancejohn.gif");
	private final ImageIcon RUA= new ImageIcon("RUAnew.gif");
	private final ImageIcon empty= new ImageIcon("emptyNew.png");
	private final ImageIcon redSqr= new ImageIcon("RedSqr.png");
	private final ImageIcon YellowSqr= new ImageIcon("YellowSqr.png");
	private final ImageIcon trollRed= new ImageIcon("TROLLred.png");
	private final ImageIcon trollYellow= new ImageIcon("TROLLYellow.png");
	private JLabel [][] board = new JLabel[6][7];
	private int[][] cords = new int[6][7];
	private int[] vertCount= new int[7];
	Font font1 = new Font("Sansarif", Font.BOLD, 15);
	Font font2 = new Font("Sansarif", Font.BOLD, 100);



	//create buttons
	private JButton JB1 = new JButton("0");
	private JButton JB2 = new JButton("1");
	private JButton JB3 = new JButton("2");
	private JButton JB4 = new JButton("3");
	private JButton JB5 = new JButton("4");
	private JButton JB6 = new JButton("5");
	private JButton JB7 = new JButton("6");
	private JButton JBreset = new JButton("R   E   S   E   T");
	private JButton secretTunnel= new JButton();
	private JButton secretTunnel2= new JButton();
	private JButton unicornSmash=new JButton();

	GUICLASS() // constructor
	{
		this.setLayout(new BorderLayout());
		JPanel headers = new JPanel();
		headers.setLayout(new GridLayout(1,7));	//makes headers
		headers.setBorder(BorderFactory.createLineBorder (new Color(153,217,234), 5));	//gives a border
		headers.setPreferredSize(new Dimension(20,50));

		//sets the sizes of the button
		JB1.setPreferredSize(new Dimension(176,80));


		//makes the matrix of buttons
		JPanel gridboard=new JPanel();
		gridboard.setLayout(new GridLayout(6,7,20,0));
		for (int i=0;i<6;i++)
		{
			for (int j=0;j<7;j++)
			{
				gridboard.add(board[i][j] = new JLabel());
				board[i][j].setIcon(empty);
			}
		}

		//adds the buttons to headers
		headers.add(JB1);
		headers.add(JB2);
		headers.add(JB3);
		headers.add(JB4);
		headers.add(JB5);
		headers.add(JB6);
		headers.add(JB7);

		//customizes reset button
		JBreset.setPreferredSize(new Dimension(1000,40));
		JBreset.setBorder(BorderFactory.createLineBorder (new Color(153,217,234), 5));
		JBreset.setFont(font1);


		//add the sub panels
		JPanel east=new JPanel();

		this.add(headers, BorderLayout.NORTH);
		this.add(gridboard, BorderLayout.CENTER);
		this.add(JBreset, BorderLayout.SOUTH);
		this.add(east, BorderLayout.EAST);
		this.add(secretTunnel2, BorderLayout.WEST);

		east.setLayout(new GridLayout(1,2,0,0));
		east.add(unicornSmash);
		east.add(secretTunnel);

		unicornSmash.setPreferredSize(new Dimension(2,800));
		secretTunnel.setPreferredSize(new Dimension(2,800));
		secretTunnel2.setPreferredSize(new Dimension(2,0));

		// add the action listeners
		JB1.addActionListener(new ButtonListener());
		JB2.addActionListener(new ButtonListener());
		JB3.addActionListener(new ButtonListener());
		JB4.addActionListener(new ButtonListener());
		JB5.addActionListener(new ButtonListener());
		JB6.addActionListener(new ButtonListener());
		JB7.addActionListener(new ButtonListener());
		JBreset.addActionListener(new ButtonListener());
		secretTunnel.addActionListener(new ButtonListener());
		secretTunnel2.addActionListener(new ButtonListener());
		unicornSmash.addActionListener(new ButtonListener());

		//for loop to set defualt of an array to 5
		for (int i=0;i<7;i++)
		{
			vertCount[i]=5;
		}

		//nested for loop to set default of matrix to 2
		for (int i=0;i<6;i++)
		{
			for (int j=0;j<7;j++)
			{
				cords[i][j]=2;
			}
		}

		timer = new Timer(delay, new TimeListener());

	}

	public void whosTurn(int row, int col, int runningCount)
	{
		if (runningCount%2==0){red(row, col);}	 //passes the verticle count for the colomn selected into the function red
		else {Yellow(row, col);}		//passes the verticle count for the colomn selected into the function Yellow
	}


	public void red(int row, int col)		//changes the appropriate square to red
	{
		if (row<0){messageChoice();} 
		else 
		{
			timer.start();
			cords[row][col]=1;
			vertCount[col]--;	//lowers the verticle count for the comlumn clicked
		} 
	}


	public void Yellow(int row, int col) 		//changes the approrpaite square to Yellow
	{
		if (vertCount[col]<0){messageChoice();}
		else 
		{
			timer.start();
			cords[row][col]=0;
			vertCount[col]--; //lowers the verticle count
		}
	}

	public void resetGame()	//resets the game
	{
		for (int i=0;i<6;i++){for (int j=0;j<7;j++){board[i][j].setIcon(empty);}}
		for (int i=0;i<7;i++){vertCount[i]=5;}
		for (int i=0;i<6;i++){for (int j=0;j<7;j++){cords[i][j]=2;}
		JB1.setEnabled(true);
		JB2.setEnabled(true);
		JB3.setEnabled(true);
		JB4.setEnabled(true);
		JB5.setEnabled(true);
		JB6.setEnabled(true);
		JB7.setEnabled(true);
		JBreset.setText("R   E   S   E   T");
		choiceCount=0;
		timer.stop();
		timerCountRed=0;
		timerCountYellow=0;
		fallingRowRed=0;
		fallingRowYellow=0;
		danceCount=0;
		JBreset.setPreferredSize(new Dimension(1000,40));
		JBreset.setFont(font1);
		
		}
	}


	public void messageChoice() 
	{
		switch (choiceCount)
		{
		case 0: JOptionPane.showMessageDialog(null,"Sorry, that column is full");break;
		case 1: JOptionPane.showMessageDialog(null,"hey woahhh there. it is still full");break;
		case 2: JOptionPane.showMessageDialog(null,"ok seriously, you can't do that");break;
		case 3: JOptionPane.showMessageDialog(null,"you're never going to be able to do that");break;
		case 4: JOptionPane.showMessageDialog(null,"wow, stop trying to cheat...not cool");break;
		case 5: JOptionPane.showMessageDialog(null,"AGAIN? are you kidding me?? you CANNOT do that!");break;
		case 6: JOptionPane.showMessageDialog(null,"DUDE. GIVE UP. IT WILL NEVER WORK.");break;
		case 7: JOptionPane.showMessageDialog(null,"that's it. I'm just gonna shut down.");break;
		case 8: JOptionPane.showMessageDialog(null,"I'm not kidding. I'll do it. DONT MAKE ME.");break;
		case 9: JOptionPane.showMessageDialog(null,"AIGHT PUNK, YA KNOW WHAT? FINE. I'M DONE HERE");System.exit(1);
		}
		runningCount--;
		choiceCount++;
		return;
	}

	public void win(int row, int col)
	{


		if ((vertCount[0]==0)&&(vertCount[1]==0)&&(vertCount[2]==0)&&(vertCount[3]==0)&&(vertCount[4]==0)&&(vertCount[5]==0)&&(vertCount[6]==0))
		{tieMessage();}

		if (runningCount%2==0){redWin(row, col);}
		else{YellowWin(row, col);}
	}

	public void redWin(int row, int col) // checks to see if red won
	{
		hoRed(row, col);
		vertRed(row, col);
		diagRed(row, col);
	}

	public void hoRed(int row, int col)
	{
		if (col==0||col==1||col==2||col==3){hz1R(row,col);}
		if (col==1||col==2||col==3||col==4){hz2R(row,col);}
		if (col==2||col==3||col==4||col==5){hz3R(row,col);;}
		if (col==3||col==4||col==5||col==6){hz4R(row,col);}
	}

	public void hz1R(int row, int col)
	{if ((cords[row][col+1]==1)&&(cords[row][col+2]==1)&&(cords[row][col+3]==1)){redMessage();}}

	public void hz2R(int row, int col)
	{if ((cords[row][col-1]==1)&&(cords[row][col+1]==1)&&(cords[row][col+2]==1)){redMessage();}}

	public void hz3R(int row, int col)
	{if ((cords[row][col-2]==1)&&(cords[row][col-1]==1)&&(cords[row][col+1]==1)){redMessage();}}

	public void hz4R(int row, int col)
	{if ((cords[row][col-3]==1)&&(cords[row][col-2]==1)&&(cords[row][col-1]==1)){redMessage();}}


	public void vertRed(int row, int col)
	{

		if (row==2||row==1||row==0){vert4R(row,col);}
	}

	public void vert4R(int row, int col)
	{if ((cords[row+3][col]==1)&&(cords[row+2][col]==1)&&(cords[row+1][col]==1)){redMessage();}}


	public void diagRed(int row, int col)
	{
		if ((row>=3)&&(col<=3)){diag1R(row,col);}
		if ((row>=3)&&(col>=3)){diag2R(row,col);}
		if ((row>=2)&&(row<=4)&&(col>=1)&&(col<=4)){diag3R(row,col);}
		if ((row>=2)&&(row<=4)&&(col>=2)&&(col<=5)){diag4R(row,col);}
		if ((row>=1)&&(row<=3)&&(col>=1)&&(col<=4)){diag5R(row,col);}
		if ((row>=1)&&(row<=3)&&(col>=2)&&(col<=5)){diag6R(row,col);}
		if ((row>=0)&&(row<=2)&&(col<=3)){diag7R(row,col);}
		if ((row>=0)&&(row<=2)&&(col>=3)){diag8R(row,col);}
	}

	public void diag1R(int row, int col)
	{if ((cords[row-1][col+1]==1)&&(cords[row-2][col+2]==1)&&(cords[row-3][col+3]==1)){redMessage();}}

	public void diag2R(int row, int col)
	{if ((cords[row-1][col-1]==1)&&(cords[row-2][col-2]==1)&&(cords[row-3][col-3]==1)){redMessage();}}

	public void diag3R(int row, int col)
	{if ((cords[row+1][col-1]==1)&&(cords[row-1][col+1]==1)&&(cords[row-2][col+2]==1)){redMessage();}}

	public void diag4R(int row, int col)
	{if ((cords[row+1][col+1]==1)&&(cords[row-1][col-1]==1)&&(cords[row-2][col-2]==1)){redMessage();}}

	public void diag5R(int row, int col)
	{if ((cords[row+2][col+2]==1)&&(cords[row+1][col+1]==1)&&(cords[row-1][col-1]==1)){redMessage();}}

	public void diag6R(int row, int col)
	{if ((cords[row+2][col-2]==1)&&(cords[row+1][col-1]==1)&&(cords[row-1][col+1]==1)){redMessage();}}

	public void diag7R(int row, int col)
	{if ((cords[row+3][col+3]==1)&&(cords[row+2][col+2]==1)&&(cords[row+1][col+1]==1)){redMessage();}}

	public void diag8R(int row, int col)
	{if ((cords[row+3][col-3]==1)&&(cords[row+2][col-2]==1)&&(cords[row+1][col-1]==1)){redMessage();}}

	public void YellowWin(int row, int col) // checks to see if red won
	{
		hoYellow(row, col);
		vertYellow(row, col);
		diagYellow(row, col);
	}

	public void hoYellow(int row, int col)
	{
		if (col==0||col==1||col==2||col==3){hz1Y(row,col);}
		if (col==1||col==2||col==3||col==4){hz2Y(row,col);}
		if (col==2||col==3||col==4||col==5){hz3Y(row,col);}
		if (col==3||col==4||col==5||col==6){hz4Y(row,col);}
	}

	public void hz1Y(int row, int col)
	{if ((cords[row][col+1]==0)&&(cords[row][col+2]==0)&&(cords[row][col+3]==0)){YellowMessage();}}

	public void hz2Y(int row, int col)
	{if ((cords[row][col-1]==0)&&(cords[row][col+1]==0)&&(cords[row][col+2]==0)){YellowMessage();}}

	public void hz3Y(int row, int col)
	{if ((cords[row][col-2]==0)&&(cords[row][col-1]==0)&&(cords[row][col+1]==0)){YellowMessage();}}

	public void hz4Y(int row, int col)
	{if ((cords[row][col-3]==0)&&(cords[row][col-2]==0)&&(cords[row][col-1]==0)){YellowMessage();}}


	public void vertYellow(int row, int col)
	{

		if (row==2||row==1||row==0){vert4Y(row,col);}
	}

	public void vert4Y(int row, int col)
	{if ((cords[row+3][col]==0)&&(cords[row+2][col]==0)&&(cords[row+1][col]==0)){YellowMessage();}}

	public void diagYellow(int row, int col)
	{
		if ((row>=3)&&(col<=3)){diag1Y(row,col);}
		if ((row>=3)&&(col>=3)){diag2Y(row,col);}
		if ((row>=2)&&(row<=4)&&(col>=1)&&(col<=4)){diag3Y(row,col);}
		if ((row>=2)&&(row<=4)&&(col>=2)&&(col<=5)){diag4Y(row,col);}
		if ((row>=1)&&(row<=3)&&(col>=1)&&(col<=4)){diag5Y(row,col);}
		if ((row>=1)&&(row<=3)&&(col>=2)&&(col<=5)){diag6Y(row,col);}
		if ((row>=0)&&(row<=2)&&(col<=3)){diag7Y(row,col);}
		if ((row>=0)&&(row<=2)&&(col>=3)){diag8Y(row,col);}
	}

	public void diag1Y(int row, int col)
	{if ((cords[row-1][col+1]==0)&&(cords[row-2][col+2]==0)&&(cords[row-3][col+3]==0)){YellowMessage();}}

	public void diag2Y(int row, int col)
	{if ((cords[row-1][col-1]==0)&&(cords[row-2][col-2]==0)&&(cords[row-3][col-3]==0)){YellowMessage();}}

	public void diag3Y(int row, int col)
	{if ((cords[row+1][col-1]==0)&&(cords[row-1][col+1]==0)&&(cords[row-2][col+2]==0)){YellowMessage();}}

	public void diag4Y(int row, int col)
	{if ((cords[row+1][col+1]==0)&&(cords[row-1][col-1]==0)&&(cords[row-2][col-2]==0)){YellowMessage();}}

	public void diag5Y(int row, int col)
	{if ((cords[row+2][col+2]==0)&&(cords[row+1][col+1]==0)&&(cords[row-1][col-1]==0)){YellowMessage();}}

	public void diag6Y(int row, int col)
	{System.out.println("Diag6Y");if ((cords[row+2][col-2]==0)&&(cords[row+1][col-1]==0)&&(cords[row-1][col+1]==0)){YellowMessage();}}

	public void diag7Y(int row, int col)
	{if ((cords[row+3][col+3]==0)&&(cords[row+2][col+2]==0)&&(cords[row+1][col+1]==0)){YellowMessage();}}

	public void diag8Y(int row, int col)
	{if ((cords[row+3][col-3]==0)&&(cords[row+2][col-2]==0)&&(cords[row+1][col-1]==0)){YellowMessage();}}

	public void hankDance(){for (int i=0;i<6;i++){for (int j=0;j<7;j++)
	{
		if (cords[i][j]==2){board[i][j].setIcon(hankDance);}
	}}}

	public void johnDance(){for (int i=0;i<6;i++){for (int j=0;j<7;j++)
	{
		if (cords[i][j]==2){board[i][j].setIcon(johnDance);}
	}}}

	public void redMessage()
	{
		JB1.setEnabled(false);
		JB2.setEnabled(false);
		JB3.setEnabled(false);
		JB4.setEnabled(false);
		JB5.setEnabled(false);
		JB6.setEnabled(false);
		JB7.setEnabled(false);
		danceCount=1;
		JOptionPane.showMessageDialog(null,"PIZZA JOHN WINS!!!\n Press enter to start a new game!");
		resetGame();
	}
	public void YellowMessage()
	{
		JB1.setEnabled(false);
		JB2.setEnabled(false);
		JB3.setEnabled(false);
		JB4.setEnabled(false);
		JB5.setEnabled(false);
		JB6.setEnabled(false);
		JB7.setEnabled(false);
		danceCount=-1;
		JOptionPane.showMessageDialog(null,"CORNDOG HANK WINS!!!\n Press enter to start a new game!");
		resetGame();
	}
	public void tieMessage()
	{
		JOptionPane.showMessageDialog(null,"TIE!!!");
		JB1.setEnabled(false);
		JB2.setEnabled(false);
		JB3.setEnabled(false);
		JB4.setEnabled(false);
		JB5.setEnabled(false);
		JB6.setEnabled(false);
		JB7.setEnabled(false);
		resetGame();
	}

	private class ButtonListener implements ActionListener  
	{
		public void actionPerformed(ActionEvent event)
		{

			if(event.getSource()==JB1)
			{
				int col=Integer.parseInt(JB1.getText());
				lastButton=col;
				int row=vertCount[col];
				whosTurn(row, col, runningCount);
				win(vertCount[col]+1,col);
				return;
			}

			if(event.getSource()==JB2)
			{
				int col=Integer.parseInt(JB2.getText());
				lastButton=col;
				int row=vertCount[col];
				whosTurn(row, col, runningCount);
				win(vertCount[col]+1,col);
				return; 
			}

			if(event.getSource()==JB3)
			{
				int col=Integer.parseInt(JB3.getText());
				lastButton=col;
				int row=vertCount[col];
				whosTurn(row, col, runningCount);
				win(vertCount[col]+1,col);
				return; 
			}

			if(event.getSource()==JB4)
			{
				int col=Integer.parseInt(JB4.getText());
				lastButton=col;
				int row=vertCount[col];
				whosTurn(row, col, runningCount);
				win(vertCount[col]+1,col);
				return; 
			}

			if(event.getSource()==JB5)
			{
				int col=Integer.parseInt(JB5.getText());
				lastButton=col;
				int row=vertCount[col];
				whosTurn(row, col, runningCount);
				win(vertCount[col]+1,col);
				return; 
			}

			if(event.getSource()==JB6)
			{
				int col=Integer.parseInt(JB6.getText());
				lastButton=col;
				int row=vertCount[col];
				whosTurn(row, col, runningCount);
				win(vertCount[col]+1,col);
				return; 
			}

			if(event.getSource()==JB7)
			{

				int col=Integer.parseInt(JB7.getText());
				lastButton=col;
				int row=vertCount[col];
				whosTurn(row, col, runningCount);
				win(vertCount[col]+1,col);
				return; 
			}

			if(event.getSource()==JBreset)
			{resetGame();return;}

			if(event.getSource()==secretTunnel)
			{timer.stop();for (int i=0;i<6;i++){for (int j=0;j<7;j++){board[i][j].setIcon(trollRed);}}redMessage();}

			if(event.getSource()==secretTunnel2)
			{timer.stop();for (int i=0;i<6;i++){for (int j=0;j<7;j++){board[i][j].setIcon(trollYellow);}};YellowMessage();}

			if(event.getSource()==unicornSmash)
			{
				timer.stop();
				JBreset.setPreferredSize(new Dimension(1000,120));
				JBreset.setFont(font2);
				for (int i=0;i<6;i++)
				{
					for (int j=0;j<7;j++)
					{
						board[i][j].setIcon(RUA);}
				}
				JBreset.setText("UNICORN SMASH!!!!!");
				JB1.setEnabled(false);
				JB2.setEnabled(false);
				JB3.setEnabled(false);
				JB4.setEnabled(false);
				JB5.setEnabled(false);
				JB6.setEnabled(false);
				JB7.setEnabled(false);
			}
		}

	}

	public class TimeListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if ((runningCount)%2==0) 
			{ //animate the red falling
				JB1.setEnabled(false);
				JB2.setEnabled(false);
				JB3.setEnabled(false);
				JB4.setEnabled(false);
				JB5.setEnabled(false);
				JB6.setEnabled(false);
				JB7.setEnabled(false);
				if (timerCountRed%2==0)
				{
					//turn red on
					board[fallingRowRed][lastButton].setIcon(redSqr);
				}
				else 
				{
					//turn red off
					if (fallingRowRed<(vertCount[lastButton]+1)){board[fallingRowRed][lastButton].setIcon(empty);fallingRowRed++;}
					else 
					{
						runningCount++;
						timer.stop();
						fallingRowRed=0;
						JB1.setEnabled(true);
						JB2.setEnabled(true);
						JB3.setEnabled(true);
						JB4.setEnabled(true);
						JB5.setEnabled(true);
						JB6.setEnabled(true);
						JB7.setEnabled(true);
					}
				}
				timerCountRed++;
			}

			else 
			{ //animate the Yellow falling
				JB1.setEnabled(false);
				JB2.setEnabled(false);
				JB3.setEnabled(false);
				JB4.setEnabled(false);
				JB5.setEnabled(false);
				JB6.setEnabled(false);
				JB7.setEnabled(false);
				if (timerCountYellow%2==0)
				{
					//turn Yellow on
					board[fallingRowYellow][lastButton].setIcon(YellowSqr);
				}
				else 
				{
					//turn Yellow off
					if (fallingRowYellow<(vertCount[lastButton]+1)){board[fallingRowYellow][lastButton].setIcon(empty);fallingRowYellow++;}
					else 
					{
						runningCount++;
						timer.stop();
						fallingRowYellow=0;
						JB1.setEnabled(true);
						JB2.setEnabled(true);
						JB3.setEnabled(true);
						JB4.setEnabled(true);
						JB5.setEnabled(true);
						JB6.setEnabled(true);
						JB7.setEnabled(true);
					}
				}
				timerCountYellow++;
			}
			if (danceCount==1){johnDance();}
			if (danceCount==-1){hankDance();}
		}
	}

}


