import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class PigGameGUI {
	//SWT Properties
	protected Shell shlPigGame;
	static Button btnRoll;
	static Button btnHold;
	static Button btnNewGame;
	static Label lblDicenumber;
	static Label lblPlayer;
	static Label lblPlayer_1;
	static Label lblPscore;
	static Label lblPscore_1;
	static Label lblPcurscore;
	static Label lblPcurscore_1;
	static Label lblWin;
	
	//Game Properties
	int[] scores = new int[]{ 0,0};
	int curScore = 0;
	int activePlayer = 0;
	boolean isGameInProgress = true;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			PigGameGUI window = new PigGameGUI();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlPigGame.open();
		shlPigGame.layout();
		runGame();
		while (!shlPigGame.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	public void switchPlayer(){
	    curScore = 0;
	    lblPcurscore.setText(String.valueOf(curScore));
	    lblPcurscore_1.setText(String.valueOf(curScore));
	    activePlayer = activePlayer == 0 ? 1 : 0;
	}
	
	public void runGame() {
		btnRoll.addSelectionListener(new SelectionAdapter() {
		      public void widgetSelected(SelectionEvent event) {
		    	  if (isGameInProgress) {
		    		  int dice = (int)(Math.random()*6+1);
		    		  lblDicenumber.setText(" " + dice);
		    		  fixScore(dice);  
		    	  }
		      }
		});
		
		btnHold.addSelectionListener(new SelectionAdapter() {
		      public void widgetSelected(SelectionEvent event) {
		    	  if (isGameInProgress) {
		    		  scores[activePlayer] += curScore;
		    		  holdScore();    		  
		    	  }
		      }
		});
		
		btnNewGame.addSelectionListener(new SelectionAdapter() {
		      public void widgetSelected(SelectionEvent event) {
		    	  isGameInProgress = true;
		    	  newGame();
		      }
		});
}
	
	public void fixScore(int dice) {
	    if (dice != 1){
	        curScore += dice;
	        if (activePlayer == 0) {
	        	lblPcurscore.setText(String.valueOf(curScore));
	        }
	        else {
	        	lblPcurscore_1.setText(String.valueOf(curScore));
	        }
	    }
	    else {
	    	switchPlayer();
	    }
	}
	
	public void holdScore() {
  	  if (activePlayer == 0) {
		  lblPscore.setText(String.valueOf(scores[activePlayer]));
      }
      else {
        	lblPscore_1.setText(String.valueOf(scores[activePlayer]));
      }
  	  
	  if (scores[activePlayer] >= 20){
	        isGameInProgress = false;
	        lblWin.setText("Player " + (activePlayer + 1) + " WON !");
	  } 
	  else {    
	    switchPlayer();
	  }	  
}
	
	public void newGame() {
		 lblPscore.setText("0");
		 lblPcurscore_1.setText("0");
		 lblPscore_1.setText("0");
		 lblPcurscore_1.setText("0");
		 lblWin.setText("");
		 scores[0] = 0;
		 scores[1] = 0;
		 switchPlayer();
	}
	
	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlPigGame = new Shell();
		shlPigGame.setSize(450, 300);
		shlPigGame.setText("Pig Game");
	
		lblDicenumber = new Label(shlPigGame, SWT.NONE);
		lblDicenumber.setAlignment(SWT.CENTER);
		lblDicenumber.setFont(SWTResourceManager.getFont("Segoe UI Historic", 10, SWT.BOLD));
		lblDicenumber.setBounds(190, 141, 55, 15);
		lblDicenumber.setText("??");
		
		btnRoll = new Button(shlPigGame, SWT.NONE);
		btnRoll.setBounds(178, 180, 75, 25);
		btnRoll.setText("Roll");
		
		btnHold = new Button(shlPigGame, SWT.NONE);
		btnHold.setBounds(178, 211, 75, 25);
		btnHold.setText("Hold");
		
		btnNewGame = new Button(shlPigGame, SWT.NONE);
		btnNewGame.setBounds(178, 10, 75, 25);
		btnNewGame.setText("New Game");
		
		lblPlayer = new Label(shlPigGame, SWT.NONE);
		lblPlayer.setBounds(39, 180, 55, 15);
		lblPlayer.setText("Player 1");
		
		lblPlayer_1 = new Label(shlPigGame, SWT.NONE);
		lblPlayer_1.setBounds(323, 180, 55, 15);
		lblPlayer_1.setText("Player 2");
		
		lblPscore = new Label(shlPigGame, SWT.NONE);
		lblPscore.setAlignment(SWT.CENTER);
		lblPscore.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblPscore.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblPscore.setBounds(39, 35, 55, 15);
		lblPscore.setText("0");
		
		lblPscore_1 = new Label(shlPigGame, SWT.NONE);
		lblPscore_1.setAlignment(SWT.CENTER);
		lblPscore_1.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblPscore_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lblPscore_1.setBounds(323, 35, 55, 15);
		lblPscore_1.setText("0");
		
		lblPcurscore = new Label(shlPigGame, SWT.NONE);
		lblPcurscore.setAlignment(SWT.CENTER);
		lblPcurscore.setFont(SWTResourceManager.getFont("Yu Gothic UI Semibold", 10, SWT.BOLD));
		lblPcurscore.setBounds(39, 223, 55, 15);
		lblPcurscore.setText("0");
		
		lblPcurscore_1 = new Label(shlPigGame, SWT.NONE);
		lblPcurscore_1.setAlignment(SWT.CENTER);
		lblPcurscore_1.setFont(SWTResourceManager.getFont("Yu Gothic UI Semibold", 10, SWT.BOLD));
		lblPcurscore_1.setBounds(323, 223, 55, 15);
		lblPcurscore_1.setText("0");
		
		lblWin = new Label(shlPigGame, SWT.NONE);
		lblWin.setAlignment(SWT.CENTER);
		lblWin.setFont(SWTResourceManager.getFont("Open Sans", 15, SWT.NORMAL));
		lblWin.setForeground(SWTResourceManager.getColor(SWT.COLOR_MAGENTA));
		lblWin.setBounds(119, 88, 207, 35);
		lblWin.setText(" ");
	}
}
