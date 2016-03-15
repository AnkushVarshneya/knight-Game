/**
 * @(#)AStarHeuristic.java
 * Assignment#1
 * @author Ankush Varshneya
 * @student# 100853074
 * AStar Heuristic
 */

package artificialintelligence;

import java.awt.Point;
import java.util.Comparator;

import gameobjects.GameTypeEnum;
import gameobjects.Pawn;

public class AStarHeuristic implements Comparator<Node> {
	private GameTypeEnum type;
	
	public AStarHeuristic(GameTypeEnum type) {
		this.type = type;
	}

	/**
	 * Comparator for A Star computes f in f = g + h
	 */
	@Override
	public int compare(Node o1, Node o2) {
		return (o1.getPathCost() + getHeuristic(o1))		// f(o1) = g(o1) + h(o1)
				- (o2.getPathCost() + getHeuristic(o2));	// f(o2) = g(o2) + h(o2)
	}

	/**
	 * Get h in f = g + h
	 * @param o1 the node
	 * @return the heuristic cost
	 */
	public int getHeuristic(Node n) {
		// TODO Auto-generated method stub
		switch(this.type){
		case ASTAREUCLIDEAN:
			return aStarEuclidean(n);
		case ASTARMANHATTEN:
			return aStarManhatten(n);
		case ASTAREUCLIDEANMANHATTEN:
			return aStarEuclideanManhatten(n);
		default:
			return 0; // else just 0
		}
	}
	
	/**
	 * Gets the sum of all Euclidean distance(s)
	 * between the knight and all pawns
	 * @param n the node
	 * @return the h value
	 */
	private int aStarEuclidean(Node n) {
		int distance = 0;
		Point knight = n.getState().getKnight().getPosition();
		for(Pawn p: n.getState().getPawns()){
			distance += knight.distance(p.getPosition());
		}
		return distance;
	}

	/**
	 * Gets the sum of all Manhattan distance(s)
	 * between the knight and all pawns
	 * @param n the node
	 * @return the h value
	 */
	private int aStarManhatten(Node n) {
		int distance = 0;
		Point knight = n.getState().getKnight().getPosition();
		for(Pawn p: n.getState().getPawns()){
			distance += Math.abs(knight.x - p.getPosition().x);
			distance += Math.abs(knight.y - p.getPosition().y);
		}
		return distance;
	}

	/**
	 * Gets sum of all Euclidean+Manhattan distance(s)
	 * between the knight and all pawns
	 * @param n the node
	 * @return the h value
	 */
	private int aStarEuclideanManhatten(Node n) {
		return aStarEuclidean(n) + aStarManhatten(n);
	}
}