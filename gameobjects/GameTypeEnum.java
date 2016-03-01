/**
 * @(#)GameTypeEnum.java
 * Assignment#1
 * @author Ankush Varshneya
 * @student# 100853074
 * The Game Type Enum
 */

package gameobjects;

public enum GameTypeEnum {
	REGULAR("Regular Game"), 
	BFS("BFS Auto Solve"),
	DFS("DFS Auto Solve"),
	ASTAREUCLIDEAN("A* Euclidean Auto Solve"),
	ASTARMANHATTEN("A* Manhatten Auto Solve"),
	ASTAREUCLIDEANMANHATTEN("A* Euclidean Manhatten Combo Auto Solve");
	
	private String displayName;
	
	/**
	 * Main constructor
	 * @param dn display name
	 */
	private GameTypeEnum(String dn){
		this.displayName = dn;
	}

	public String getDisplayName() { return displayName; }
	
	/**
	 * toString does display name
	 */
	public String toString() {
		return displayName;
	}
}
