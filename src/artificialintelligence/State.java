/**
 * @(#)State.java
 * Assignment#1
 * @author Ankush Varshneya
 * @student# 100853074
 * Used to represent a state in the game needed by node
 */

package artificialintelligence;

import java.util.ArrayList;

import game.Model;
import gameobjects.Knight;
import gameobjects.Pawn;

public class State {
	private Knight			knight; 	// Knight
	private ArrayList<Pawn>	pawns;		// all the pawns.
	private int				dimension; 	// dimension of the board
	
    // Public methods to allow access to private variable.
	public Knight			getKnight()			{ return knight; }
	public ArrayList<Pawn>	getPawns()			{ return pawns; }
	public int				getDimensions() 	{ return this.dimension; }

	public void setKnight(Knight knight)		{ this.knight = knight; }
	public void setPawns(ArrayList<Pawn> pawns)	{ this.pawns = pawns; }
	
	/**
	 * Main constructor 
	 * @param k knight
	 * @param p pawns
	 */
    public State(Knight k, ArrayList<Pawn> p, int d) {
		this.knight = k;
		this.pawns = p;
		this.dimension = d;
	}

	/**
	 * Make state from a model problem
	 * Needed for initial state
	 */
    public State(Model problem) {
    	this.knight = new Knight(problem.getKnight());
    	
    	this.pawns  = new ArrayList<Pawn>();
		problem.getPawnList().forEach((elem) -> {
			this.pawns.add(new Pawn(elem));
		});
		
		this.dimension = problem.getDimentions();
	}

    /**
	 * Copy constructor
	 * @param other the state to copy from
	 */
	public State(State other) {
    	this.knight = new Knight(other.getKnight());
		
    	this.pawns  = new ArrayList<Pawn>();
    	other.getPawns().forEach((elem) -> {
			this.pawns.add(new Pawn(elem));
		});
    	
    	this.dimension = other.getDimensions();
	}

	/**
	 * Returns true if at goal state ie no pawns left
	 * @return
	 */
	public boolean isGoalState() {
		return this.pawns.isEmpty();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((knight == null) ? 0 : knight.hashCode());
		result = prime * result + ((pawns == null) ? 0 : pawns.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		State other = (State) obj;
		if (knight == null) {
			if (other.knight != null)
				return false;
		} else if (!knight.equals(other.knight))
			return false;
		if (pawns == null) {
			if (other.pawns != null)
				return false;
		} else if (!pawns.equals(other.pawns))
			return false;
		return true;
	}
	
    public void LogBoard() {
    	String s[][] = new String[this.dimension][this.dimension];
    	s[this.knight.getPosition().x][this.knight.getPosition().y] = "K";
    	this.pawns.forEach((pawn)-> {
        	s[pawn.getPosition().x][pawn.getPosition().y] = "P";

    	});
    	
    	for(int i=0; i < this.dimension; i++){
        	for(int j=0; j < this.dimension; j++){
        		System.out.print((s[i][j]==null)?"*":s[i][j]);        		
        	}
        	System.out.print("\n");
    	}
    	System.out.print("\n");
    }
}