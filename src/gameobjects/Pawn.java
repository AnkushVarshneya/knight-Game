/**
 * @(#)Pawn.java
 * Assignment#1
 * @author Ankush Varshneya
 * @student# 100853074
 * Pawn Model
 */

package gameobjects;

import java.awt.Point;

public class Pawn {
	private	Point 			position;			// pawns current position
	private	DirectionEnum 	movingDirection;	// pawns moving direction

    // Public methods to allow access to view private variable.
	public Point getPosition() 					{ return position; }
	public DirectionEnum getMovingDirection() 	{ return movingDirection; }

    /**
     * Main constructor 
     * @param p	position
     * @param d	direction
     */
    public Pawn(Point p, DirectionEnum d) {
    	this.position = p;
    	this.movingDirection = d;
    }
    
    /**
     * Copy constructor 
     * @param other the other pawn
     */
    public Pawn(Pawn other) {
    	this.position = new Point(other.position);
    	this.movingDirection = other.movingDirection;
    }
    
    /**
     * Move the pawn in moving direction and change moving direction for next time
     */
    public void movePawn(){
    	DirectionEnum.movePoint(this.position, this.movingDirection);
    	this.movingDirection = DirectionEnum.opposite(this.movingDirection);
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
		Pawn other = (Pawn) obj;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		return true;
	};

}
