/**
 * @(#)Board.java
 * Assignment#1
 * @author Ankush Varshneya
 * @student# 100853074
 * Board View
 */

package gameobjects;

import javax.swing.*;
import java.awt.*;

import game.Model;
import image.Icon;

@SuppressWarnings("serial")
public class Board extends JPanel {
	private Model 		model; 			// The model to which this view is attached.
	private DButton[][]	squareButtons; 	// square button array.

    // Public methods to allow access to view private variable.
	public DButton[][]	getSquareButtons() 	{ return this.squareButtons; }
	public Model		getModel() 			{ return this.model; }

	/**
     * Main constructor 
	 * @param m the model
	 */
    public Board(Model m) {
    	this.model = m;

    	// Choose grid layout.
    	this.setLayout(new GridLayout(this.model.getDimentions(), this.model.getDimentions()));

		// Initialize arrays of JButtons.
    	this.squareButtons = new DButton[model.getDimentions()][model.getDimentions()];

    	// Sets board.
		for(int y = 0; y < this.model.getDimentions(); y++) {
			for(int x = 0; x < this.model.getDimentions(); x++) {
    			// Make the board's buttons
    			this.squareButtons[x][y] = new DButton();
    			// select normal button icon unless its a border
    			if ( x == 0 || x == this.model.getDimentions() - 1 ||
    				 y == 0 || y == this.model.getDimentions() - 1 ) {
        			this.squareButtons[x][y].setBackground(Color.darkGray);
    			} else {
    				this.squareButtons[x][y].setBackground(((x+y)%2 == 0)? Color.lightGray:Color.white);
    			}
    			// Add the button to the panel.
				this.add(squareButtons[x][y]);
				// Set the button unselected.
				this.squareButtons[x][y].setSelected(false);
				// Set all buttons disabled.
				this.squareButtons[x][y].setEnabled(false);
    		}
    	}
    	
    	// set pawns
    	this.setPawns();
    	
    	// set knights and moves
    	this.setKnight();
    }
        
    /**
     * Sets the knight and its moves on the board
     */
    public void setKnight() {
    	this.squareButtons[this.model.getKnight().getPosition().x][this.model.getKnight().getPosition().y].setIcon(Icon.Knight);
    	this.squareButtons[this.model.getKnight().getPosition().x][this.model.getKnight().getPosition().y].setDisabledIcon(Icon.Knight);

    	this.model.getKnight().nextMoves().forEach((k,v) -> {
			this.squareButtons[v.x][v.y].setBackground(Color.green);
			this.squareButtons[v.x][v.y].setEnabled(true);
    	});
	}
	
    /**
     * Disables knights current moves
     */
    public void deSetKnight() {
    	this.squareButtons[this.model.getKnight().getPosition().x][this.model.getKnight().getPosition().y].setIcon(null);
    	this.squareButtons[this.model.getKnight().getPosition().x][this.model.getKnight().getPosition().y].setDisabledIcon(null);

    	this.model.getKnight().nextMoves().forEach((k,v) -> {
    		this.squareButtons[v.x][v.y].setBackground(((v.x+v.y)%2 == 0)? Color.lightGray:Color.white);
    		this.squareButtons[v.x][v.y].setEnabled(false);
    	});
	}
    
    /**
     * Sets the pawns at new position
     */
    public void setPawns() {
    	this.model.getPawnList().forEach((pawn)-> {
        	this.squareButtons[pawn.getPosition().x][pawn.getPosition().y].setIcon(Icon.Pawn);
        	this.squareButtons[pawn.getPosition().x][pawn.getPosition().y].setDisabledIcon(Icon.Pawn);

    	});
	}
	
    /**
     * remove the pawns from old position
     */
    public void deSetPawns() {
    	this.model.getPawnList().forEach((pawn)-> {
        	this.squareButtons[pawn.getPosition().x][pawn.getPosition().y].setIcon(null);
        	this.squareButtons[pawn.getPosition().x][pawn.getPosition().y].setDisabledIcon(null);

    	});
	}
        
    /**
     * Testing
     * @param args
     */
    public static void main(String[] args) {
    	Model aModel = new Model();
		Board aView = new Board(aModel);

		JFrame frame = new JFrame("The Knight Game Board Tester");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(702, 721); // Manually computed sizes.
		frame.getContentPane().add(aView);
		frame.setVisible(true);
	}
}