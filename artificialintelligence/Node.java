/**
 * @(#)Node.java
 * Assignment#1
 * @author Ankush Varshneya
 * @student# 100853074
 * Used to represent a node in the search
 */

package artificialintelligence;

import gameobjects.DirectionEnum;

public class Node {
	private State 			state;		// state of node
	private DirectionEnum	action;		// action of node
	private Node			parent;		// the parent of this node, can be null if root
	private int				pathCost;	// total cost to get to goal or g in f = g + h
	
	public State 			getState()			{ return state; }
	public DirectionEnum 	getAction()			{ return action; }
	public Node 			getParent() 		{ return parent; }
	public int 				getPathCost()		{ return pathCost; }
	
	public void setState(State state) 			{ this.state = state; }
	public void setAction(DirectionEnum action)	{ this.action = action;	}
	public void setParent(Node parent)			{ this.parent = parent;	}
	public void setPathCost(int pathCost) 		{ this.pathCost = pathCost; }

	
	/**
	 * Main constructor 
	 * @param state state to make it for.
	 */
	public Node(State state) {
		this.state = state;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((state == null) ? 0 : state.hashCode());
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
		Node other = (Node) obj;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}	
}
