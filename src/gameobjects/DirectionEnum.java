/**
 * @(#)DirectionEnum.java
 * Assignment#1
 * @author Ankush Varshneya
 * @student# 100853074
 * The Direction Enum
 */

package gameobjects;

import java.awt.Point;

public enum DirectionEnum {
	LEFT, RIGHT, DOWN, UP, LEFTUP, LEFTDOWN, RIGHTUP, RIGHTDOWN, UPLEFT, UPRIGHT, DOWNLEFT, DOWNRIGHT;
	
	
	/**
	 * Finds if the given direction is a knight move
	 * @param direction	Some direction
	 * @return if valid knight move
	 */
	public boolean isKnightMove() {
		return 	this != UP && 
				this != DOWN &&
				this != LEFT &&
				this != RIGHT;
	}
	
	/**
	 * Moves the given point in the given direction
	 * @param p			Some Point
	 * @param direction	Direction to move the point
	 */
	public static void movePoint(Point p, DirectionEnum direction) {
		if 		(direction == UP) 		{ p.translate(-0,-1); }
		else if (direction == DOWN) 	{ p.translate(-0, 1); }
		else if (direction == RIGHT)	{ p.translate( 1,-0); }
		else if (direction == LEFT) 	{ p.translate(-1,-0); }
		
		else if (direction == LEFTUP) 	{ p.translate(-2,-1); }
		else if (direction == LEFTDOWN) { p.translate(-2, 1); }
		
		else if (direction == RIGHTUP) 	{ p.translate( 2,-1); }
		else if (direction == RIGHTDOWN){ p.translate( 2, 1); }
		
		else if (direction == UPLEFT) 	{ p.translate(-1,-2); }
		else if (direction == UPRIGHT) 	{ p.translate( 1,-2); }
		
		else if (direction == DOWNLEFT)	{ p.translate(-1, 2); }
		else if (direction == DOWNRIGHT){ p.translate( 1, 2); }
	}
	
	/**
	 * Gets the given point in the given direction from current point
	 * @param p			Some Point
	 * @param direction	Direction for next point
	 * @return			the resulting point
	 */
	public static Point getPointInDirection(Point p, DirectionEnum direction) {
		if 		(direction == UP) 		{ return new Point(p.x - 0, p.y - 1); }
		else if (direction == DOWN) 	{ return new Point(p.x - 0, p.y + 1); }
		else if (direction == RIGHT)	{ return new Point(p.x + 1, p.y - 0); }
		else if (direction == LEFT) 	{ return new Point(p.x - 1, p.y - 0); }
		
		else if (direction == LEFTUP) 	{ return new Point(p.x - 2, p.y - 1); }
		else if (direction == LEFTDOWN) { return new Point(p.x - 2, p.y + 1); }
		
		else if (direction == RIGHTUP) 	{ return new Point(p.x + 2, p.y - 1); }
		else if (direction == RIGHTDOWN){ return new Point(p.x + 2, p.y + 1); }
		
		else if (direction == UPLEFT) 	{ return new Point(p.x - 1, p.y - 2); }
		else if (direction == UPRIGHT) 	{ return new Point(p.x + 1, p.y - 2); }
		
		else if (direction == DOWNLEFT)	{ return new Point(p.x - 1, p.y + 2); }
		else if (direction == DOWNRIGHT){ return new Point(p.x + 1, p.y + 2); }

		else 							{ return new Point(p.x + 1, p.y + 2); }
	}

	/**
	 * Gets the opposite direction of a given direction
	 * @param direction	the current direction
	 * @return			the opposite direction
	 */
	public static DirectionEnum opposite(DirectionEnum direction) {
		if 		(direction == UP) 		return DOWN;
		else if (direction == DOWN) 	return UP;
		else if (direction == RIGHT) 	return LEFT;
		else if (direction == LEFT) 	return RIGHT;
		
		else if (direction == LEFTUP) 	return RIGHTDOWN;
		else if (direction == LEFTDOWN)	return RIGHTUP;
		
		else if (direction == RIGHTUP) 	return LEFTDOWN;
		else if (direction == RIGHTDOWN)return LEFTUP;
		
		else if (direction == UPLEFT) 	return DOWNRIGHT;
		else if (direction == UPRIGHT) 	return DOWNLEFT;

		else if (direction == DOWNLEFT) return UPRIGHT;
		else if (direction == DOWNRIGHT)return UPLEFT;

		else							return DirectionEnum.UPLEFT;
	}
}
