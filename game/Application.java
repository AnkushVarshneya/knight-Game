package game;
/**
 * @(#)Application.java
 * Assignment#1
 * @author Ankush Varshneya
 * @student# 100853074
 * Main Application / Controller
 */

import java.awt.event.*; // Needed for ActionListener.
import gameobjects.DButton;
import gameobjects.GameTypeEnum;
import gameobjects.Worker;

import javax.swing.*; // Needed for JFrame.

import java.awt.*; //Needed for Component.

@SuppressWarnings("serial")
public class Application extends JFrame implements ActionListener{

	private Model model; // The model to which this view is attached.
	private View view; // The view will show the state of the model.

	private boolean gameInProgress;

    public Model getModel() { return model;	}
	public View getView() { return view; }
	public boolean isGameInProgress() {	return gameInProgress; }

	public Application(String title) {
    	super(title); // Sets the title for the window.

    	this.view = new View(); // only make the view

		// Ads action listener to the start/stop button clicking the start button will start a new game.
		this.view.getStartStopButton().addActionListener(this);
		this.getContentPane().add(view); // Add the view.

		// Manually computed sizes.
		this.setSize(952, 721);
		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

	/**
	 * This is the single event handler for all the buttons
	 * @param e event
	 */
    public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.view.getStartStopButton()){
			if(e.getActionCommand()=="Start") {	this.handleStartButtonSelection(); } // Deal with the start button.
			else if (e.getActionCommand()=="Stop") { this.handleStopButtonSelection(); } // Deal with the stop button.
		}
		else { handleKnightMoveButtonSelection(e); } // Knight Move buttons are the other buttons remaining.
     	this.view.update();	// Update the view.
    }
	
	/**
	 * Handle knights moves
	 * @param e event
	 */
    private void handleKnightMoveButtonSelection(ActionEvent e) {
		gameInProgress=!(model.isOver());
		// If the game is not in progress simulate pressing the stop button.
		if(!gameInProgress) {
			this.handleStopButtonSelection();
		}
		// else play the move!
		else {
			// deset old knight
			this.deSetKnight();
			// move knight
			this.model.moveKnight(((DButton)e.getSource()).getDirection());
			// remove pawns at knights position
			this.model.removePawn(this.model.getKnight().getPosition());
			// deset pawns
			this.view.getBoard().deSetPawns();
			// move the pawns as the knight moved
			this.model.moveAllPawns();
			// remove moved pawns at knights position
			this.model.removePawn(this.model.getKnight().getPosition());
			// set pawns
			this.view.getBoard().setPawns();
			// set new knight
			this.setKnight();
		}
	}
    
    /**
     * Starts a new game
     */
	private void handleStartButtonSelection() {
		this.gameInProgress = true;
		
		this.model = new Model(	Integer.parseInt(this.view.getDimensionField().getText()),
								Integer.parseInt(this.view.getPawnField().getText()),
								Integer.parseInt(this.view.getFrameRateField().getText()));
		this.view.getNewBoard(this.model);
	
		// This labels the button "Stop".
		this.view.getStartStopButton().setSelected(true);
		
		// disable the parameter fields
       	this.view.getTypeList().setEnabled(false);
       	this.view.getDimensionField().setEnabled(false);
       	this.view.getPawnField().setEnabled(false);
       	this.view.getFrameRateField().setEnabled(false);
       	
       	// set pawns
     	this.view.getBoard().setPawns();
     	// set knight		
       	this.setKnight();

       	if(this.view.getTypeList().getSelectedValue() != GameTypeEnum.REGULAR) {
       		//  run worker for auto solve
       		new Worker(this).execute();
       	}
   	}

	/**
	 * Stops the current game
	 */
    private void handleStopButtonSelection() {
		this.gameInProgress = false;

    	// Get All The components of the board (the Jbuttons) and Disable all of them so we can show the board.
		Component c[] = this.view.getBoard().getComponents();
		for(int i=0; i<c.length; i++) ((JButton)c[i]).setEnabled(false);

		// This labels the button "Start".
		this.view.getStartStopButton().setSelected(false);
				
		// enable the parameter fields
       	this.view.getTypeList().setEnabled(true);
       	this.view.getDimensionField().setEnabled(true);
       	this.view.getPawnField().setEnabled(true);
       	this.view.getFrameRateField().setEnabled(true);
    }
        
    /**
     * Sets the knight and its moves on the board
     * @param knight
     */
    public void setKnight() {
    	this.view.getBoard().setKnight();
    	
    	this.view.getBoard().getModel().getKnight().nextMoves().forEach((k,v) -> {
			this.view.getBoard().getSquareButtons()[v.x][v.y].addActionListener(this);
			this.view.getBoard().getSquareButtons()[v.x][v.y].setDirection(k);
			this.view.getBoard().getSquareButtons()[v.x][v.y].setText(k.toString());
    	});
	}
	
    /**
     * Disables knights current moves
     * @param knight
     */
    public void deSetKnight() {
    	this.view.getBoard().deSetKnight();
    	
    	this.view.getBoard().getModel().getKnight().nextMoves().forEach((k,v) -> {
			this.view.getBoard().getSquareButtons()[v.x][v.y].removeActionListener(this);
			this.view.getBoard().getSquareButtons()[v.x][v.y].setText(null);
    	});
	}
    
	/**
	 *  This is where the program begins.
	 */
    public static void main(String[] args) {
		JFrame frame = new Application("The Knight Game");
		frame.setVisible(true);
	}
}