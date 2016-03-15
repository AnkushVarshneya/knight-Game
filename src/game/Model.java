/**
 * @(#)Model.java
 * Assignment#1
 * @author Ankush Varshneya
 * @student# 100853074
 * Main Model
 */

package game;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import gameobjects.*;

public class Model {
	private int 			dimension;		// Dimensions of the board.
	private int				remainingPawns;	// Pawns remaining, original number will be used to generate the pawns
	private	int				movesMade;		// moves made so far
	private int				frameRate;		// the frame rate
	private ArrayList<Pawn> pawns;			// pawns.
	private Knight			knight;			// the knight of the game
	private String			stats;			// search stat
	
	// Public methods to allow access to view private variable.
	public int				getDimentions() 	{ return this.dimension; }
	public int				getRemainingPawns() { return this.remainingPawns; }
	public int				getMovesMade() 		{ return this.movesMade; }
	public int				getFrameRate() 		{ return this.frameRate; }
	public ArrayList<Pawn>	getPawnList() 		{ return this.pawns; }
	public Knight 			getKnight() 		{ return knight; }
	public String 			getStats() 			{ return stats; }
	public void 			setStats(String s) 	{ this.stats = s; }
	
    /**
     * default constructor
     */
	public Model() {
    	this(20, 5, 1000);
    }

    /**
     * Main constructor 
     * @param d	dimensions of board
     * @param n number of pawns to start the game with
     * @param f the frame rate
     */
    public Model(int d, int n, int f) {
    	this.dimension = d;
    	this.remainingPawns = n;
    	this.movesMade = 0;
    	this.frameRate = f;
    	generatePawnPositions(d, n, this.pawns);
    	generateKnightPosition(d, this.knight);
    }

    /**
     * Generates knight with its positions
     * limits are 1 to d-2 to exclude border positions
     * alternative direction is also set
     * @param d
     * @param k
     */
    private void generateKnightPosition(int d, Knight k) {
    	Random rand = new Random();
    	if(this.frameRate == 999) { // TODO: remove
    		this.knight = new Knight(	new Point(	1,
													1),
    									this.dimension - 1 // to exclude border
    								);
    	}
    	else {    		
    		this.knight = new Knight(	new Point(	1 + rand.nextInt(d - 2),
    												1 + rand.nextInt(d - 2)),
    									this.dimension - 1 // to exclude border
    								);
    	}
	}
	
    /**
     * Generates pawns with their positions
     * limits are 2 to d-3 to exclude border positions and alternative positions
     * alternative direction is also set
     * @param d
     * @param n
     * @param p
     */
    public void generatePawnPositions(int d, int n, ArrayList<Pawn> p){
    	// first make n# of points
		Set<Point> set = new HashSet<Point>();
		Random rand = new Random();
		Point pt;

    	if(this.frameRate == 999) { // TODO: remove
    		set.add(new Point(2, 7));
    		set.add(new Point(5, 6));
    		set.add(new Point(7, 5));
    		set.add(new Point(6, 4));
    		set.add(new Point(7, 3));
    		set.add(new Point(2, 3));
    		this.remainingPawns = set.size();
    	}
    	else { 
			while (set.size()<n) {
			    pt = new Point();
			    pt.x = 2 + rand.nextInt(d - 4);
			    pt.y = 2 + rand.nextInt(d - 4);
			    set.add(pt);
			} ;
    	}
    	this.pawns = new ArrayList<Pawn>();
    	if(this.frameRate == 999) { // TODO: remove
			set.forEach(elem -> {
				this.pawns.add(new Pawn(elem,
										DirectionEnum.values()
											[1]
				));
			});    		
    	}
    	else {
			set.forEach(elem -> {
				this.pawns.add(new Pawn(elem,
										DirectionEnum.values()
											[rand.nextInt(4)]
				));
			});
    	}
    }
    
	/**
	 * @return true game is over, if there are no more matches to be made
	 */
    public boolean isOver() { return this.remainingPawns==0; }
    
    /**
     * Move the knight in a direction
     * @param direction the direction to move in
     * @return true if collided with pawn
     */
    public boolean moveKnight(DirectionEnum direction){
    	this.knight.moveKnight(direction);
    	this.movesMade++;
    	return this.pawns.contains(new Pawn(this.knight.getPosition(), null));
    };

    /**
     * Move all pawns in their respective direction
     */
    public void moveAllPawns(){
    	this.pawns.forEach((pawn)->{
    		pawn.movePawn();
    	});
    };
    
    /**
     * Remove pawn at a given position from the 
     * pawn list and lowers remaining pawns by one
     * @param position position of pawn
     */
    public void removePawn(Point position){
    	this.pawns.remove(new Pawn(position, null ));
    	this.remainingPawns = this.pawns.size();
    }
}