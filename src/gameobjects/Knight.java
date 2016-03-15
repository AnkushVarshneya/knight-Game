/**
 * @(#)Knight.java
 * Assignment#1
 * @author Ankush Varshneya
 * @student# 100853074
 * Knight Model
 */

package gameobjects;

import java.awt.Point;
import java.util.HashMap;

public class Knight {
	private Point	position;	// Knights current position
	private int		bound;		// The bound for all of knights moves
	
    // Public methods to allow access to view private variable.
	public Point getPosition()	{ return position; }

    /**
     * Main constructor 
     * @param p	position
     */
    public Knight(Point p, int b) {
    	this.position = p;
    	this.bound = b;
    }

	/**
     * Copy constructor 
     * @param other the other knight
     */
    public Knight(Knight other) {
    	this.position = new Point(other.position);
    	this.bound = other.bound;
    }
        
    /**
     * Find next move of the knight
     * @return an array of moves 
     */
    public HashMap<DirectionEnum, Point> nextMoves(){
    	HashMap<DirectionEnum, Point> ret = new HashMap<DirectionEnum, Point>();
    	for(DirectionEnum d : DirectionEnum.values()){
    		Point p = DirectionEnum.getPointInDirection(this.position, d);
    		if(	d.isKnightMove() &&
    			(p.x > 0 && p.x < this.bound) &&
    			(p.y > 0 && p.y < this.bound)) {
    			ret.put(d, p);
    		}
    	}
    	return ret;
    }    
    
    /**
     * Move the knight in a direction
     * @param direction the direction to move in
     */
    public void moveKnight(DirectionEnum direction){
    	DirectionEnum.movePoint(this.position, direction);
    }
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((position == null) ? 0 : position.hashCode());
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
		Knight other = (Knight) obj;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		return true;
	};   
}