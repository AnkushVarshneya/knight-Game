/**
 * @(#)Search.java
 * Assignment#1
 * @author Ankush Varshneya
 * @student# 100853074
 * The solution searcher
 */

package artificialintelligence;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

import game.Model;
import gameobjects.GameTypeEnum;
import gameobjects.Pawn;

public class Search {

	/**
	 * AStar search to find the solution
	 * @param problem need for make initial state
	 * @param gametype used to get heuristic 
	 * @return the last node
	 */
	public static Node AStarSearch(Model problem, GameTypeEnum gametype){
		long startTime = System.currentTimeMillis(); 							// start a timer
		int nodesProcessed = 0;												 	// nodes process in total
		AStarHeuristic	heuristic = new AStarHeuristic(gametype);				// make a heuristic based on game type 
		PriorityQueue<Node> fringe = new PriorityQueue<Node>(heuristic);		// fringe queue to keep unprocessed nodes
		LinkedList<Node> closelist = new LinkedList<Node>(); 					// close list to keep processed nodes
		HashMap<Node, Integer> totalCostToGoal = new HashMap<Node, Integer>();	// keeps g_cost on all explored nodes
		// Used to recursively update cost if a node's parent is changed
		HashMap<Node, LinkedList<Node>> nodeToChildren = new HashMap<Node, LinkedList<Node>>();

		Node initialNode = new Node(new State(problem));
		initialNode.setParent(null); 	// no parent
		initialNode.setPathCost(0);	 	// starting so no cost
		initialNode.setAction(null); 	// no action for initial node
		fringe.add(initialNode); 		// add it
		Node currentNode = null;		// initially null
		
		// add it to the total cost to goal hash map
		totalCostToGoal.put(initialNode, initialNode.getPathCost());

		// do until fringe is not empty
		while (!fringe.isEmpty()){
			currentNode = fringe.poll(); // gets it out based on priority
			
			// if at goal state return that node
			if(currentNode.getState().isGoalState()) {
				// set stat
				problem.setStats(	"Using \n"+gametype+":\n" +
									"Awnser found in "+currentNode.getPathCost()+" move(s)\n" +
									"Took " + ((System.currentTimeMillis() - startTime)/1000.0) + " seconds\n" +
									"Expanded " + nodesProcessed + " nodes");
				// TODO: remove debug
				System.out.println(problem.getStats());
				return currentNode;
			}
			
			// to avoid cycles only expand if not in closed list
			if(!closelist.contains(currentNode)){				
				// add in closed list
				if(closelist.add(currentNode)) {
					// processed another node after closing
					nodesProcessed++;
				}
				// Now expand
				LinkedList<Node> expandedChildren = expandNode(currentNode);
				
				// get / init the stored children
				LinkedList<Node> storedChildren = nodeToChildren.get(currentNode);
				if (storedChildren == null) {
					storedChildren = new LinkedList<Node>();
					nodeToChildren.put(currentNode, storedChildren);
				}
				
				// process the children
				while(!expandedChildren.isEmpty()) {
					Node childNode = expandedChildren.remove();
					boolean inClosed = closelist.contains(childNode);
					boolean inOpen = fringe.contains(childNode);
					
					// if child not seen add it to fringe and stored children on current node
					if (!inClosed && !inOpen) {
						storedChildren.add(childNode);
						totalCostToGoal.put(childNode, childNode.getPathCost());
						fringe.add(childNode);
						continue; // move on to the next child
					}
					
					// otherwise if child seen before update parent if new cost is better 
					
					// old cost is in the totalCostToGoal map
					int oldCost = totalCostToGoal.get(childNode);
					// new cost is in child
					int newCost = childNode.getPathCost();
					
					// if new cost is better update path
					if (newCost < oldCost){
						// reset parent
						childNode.setParent(currentNode);
						
						// Recursively update child's children as needed
						LinkedList<Node> childrenToRecurse = new LinkedList<Node>();
						Node recurseChild = childNode;
						do {
							// get current recurse child's children and add to childrenToRecurse
							LinkedList<Node> recurseChildren = nodeToChildren.get(recurseChild);
							recurseChildren.forEach((recursiveChild) -> {
								childrenToRecurse.addLast(recursiveChild);
							});
							
							// Make child's new cost parents cost + 1(the heuristic cost)  
							int recurseChildNewCost = totalCostToGoal.get(recurseChild.getParent()) + 1;
							recurseChild.setPathCost(recurseChildNewCost);
							totalCostToGoal.put(recurseChild, recurseChildNewCost);
							
							// Update object in fringe/closed list
							if (fringe.contains(recurseChild)) {
								fringe.remove(recurseChild);		// Removing old object which doesn't have updated cost
								fringe.add(recurseChild);			// Adding new object which has updated cost
							}
							else
								if (closelist.contains(recurseChild)) {
									closelist.remove(recurseChild); // Removing old object which doesn't have updated cost
									closelist.add(recurseChild);	// Adding new object which has updated cost
								}
							// get the next child for the next round
							childNode = childrenToRecurse.removeFirst();
							
						} while (childrenToRecurse.isEmpty());
					}
				}
			}
		}
		
		// if nothing is there then return null solution
		
		// set stat
		problem.setStats(	"Using \n"+gametype+":\n" +
							"No Awnser found\n" +
							"Took " + ((System.currentTimeMillis() - startTime)/1000.0) + " seconds\n" +
							"Expanded " + nodesProcessed + " nodes");
		// TODO: remove debug
		System.out.println(problem.getStats());
		return currentNode;
	}

	
	/**
	 * BFS search to find the solution
	 * @param problem need for make initial state
	 * @return the last node
	 */
	public static Node bfsSearch(Model problem){
		long startTime = System.currentTimeMillis(); 		 // start a timer
		int nodesProcessed = 0;								 // nodes process in total
		LinkedList<Node> fringe = new LinkedList<Node>(); 	 // fringe list to keep unprocessed nodes
		LinkedList<Node> closelist = new LinkedList<Node>(); // close list to keep processed nodes
		
		Node initialNode = new Node(new State(problem));
		initialNode.setParent(null); 	// no parent
		initialNode.setPathCost(0);	 	// starting so no cost
		initialNode.setAction(null); 	// no action for initial node
		fringe.addLast(initialNode); 	// add it
		Node node = null;				// initially null
		
		// do until fringe is not empty
		while (!fringe.isEmpty()){
			node = fringe.removeFirst();
			
			// if at goal state return that node
			if(node.getState().isGoalState()) {
				// set stat
				problem.setStats(	"Using BFS:\n" +
									"Awnser found in "+node.getPathCost()+" move(s)\n" +
									"Took " + ((System.currentTimeMillis() - startTime)/1000.0) + " seconds\n" +
									"Expanded " + nodesProcessed + " nodes");
				// TODO: remove debug
				System.out.println(problem.getStats());
				return node;
			}
			
			// to avoid cycles only expand if not in closed list
			if(!closelist.contains(node)){				
				// add in closed list
				if(closelist.add(node)) {
					// processed another node after closing
					nodesProcessed++;
				}
				// Now expand
				LinkedList<Node> expandedChildren = expandNode(node);
				// process the children
				while(!expandedChildren.isEmpty()) {
					Node childNode = expandedChildren.removeFirst();
					boolean inClosed = closelist.contains(childNode);
					boolean inOpen = fringe.contains(childNode);
					
					// add children to fringe list to process next
					if (!inClosed && !inOpen) {
						fringe.addLast(childNode);
						continue; // move on to the next child
					}
				}
			}
		}
		
		// if nothing is there then return null solution
		
		// set stat
		problem.setStats(	"Using BFS:\n" +
							"No Awnser found\n" +
							"Took " + ((System.currentTimeMillis() - startTime)/1000.0) + " seconds\n" +
							"Expanded " + nodesProcessed + " nodes");
		// TODO: remove debug
		System.out.println(problem.getStats());
		return node;
	}
	
	/**
	 * DFS search to find the solution
	 * @param problem need for make initial state
	 * @return the last node
	 */
	public static Node dfsSearch(Model problem){
		long startTime = System.currentTimeMillis(); 		 // start a timer
		int nodesProcessed =0;								 // nodes process in total						
		LinkedList<Node> fringe = new LinkedList<Node>(); 	 // fringe list to keep unprocessed nodes
		LinkedList<Node> closelist = new LinkedList<Node>(); // close list to keep processed nodes
		
		Node initialNode = new Node(new State(problem));
		initialNode.setParent(null); 	// no parent
		initialNode.setPathCost(0);	 	// starting so no cost
		initialNode.setAction(null); 	// no action for initial node
		fringe.addFirst(initialNode); 	// add it
		Node node = null;				// initially null
		
		// do until fringe is not empty
		while (!fringe.isEmpty()){
			node = fringe.removeFirst();
			
			// if at goal state return that node
			if(node.getState().isGoalState()) {
				// set stat
				problem.setStats(	"Using DFS:\n" +
									"Awnser found in "+node.getPathCost()+" move(s)\n" +
									"Took " + ((System.currentTimeMillis() - startTime)/1000.0) + " seconds\n" +
									"Expanded " + nodesProcessed + " nodes");
				// TODO: remove debug
				System.out.println(problem.getStats());
				return node;
			}
			
			// to avoid cycles only expand if not in closed list
			if(!closelist.contains(node)){			
				// add in closed list
				if(closelist.add(node)) {
					// processed another node after closing
					nodesProcessed++;
				}
				// Now expand
				LinkedList<Node> expandedChildren = expandNode(node);
				// process the children
				while(!expandedChildren.isEmpty()) {
					Node childNode = expandedChildren.removeLast();
					boolean inClosed = closelist.contains(childNode);
					boolean inOpen = fringe.contains(childNode);
					
					// add children to fringe list to process next
					if (!inClosed && !inOpen) {
						fringe.addFirst(childNode);
						continue; // move on to the next child
					}
				}
			}
		}
		
		// if nothing is there then return null solution
		
		// set stat
		problem.setStats(	"Using DFS:\n" +
							"No Awnser found\n" +
							"Took " + ((System.currentTimeMillis() - startTime)/1000.0) + " seconds\n" +
							"Expanded " + nodesProcessed + " nodes");
		// TODO: remove debug
		System.out.println(problem.getStats());
		return node;
	}
	
	/**
	 * Expand method for expanding child nodes
	 * @param problem 	the problem
	 * @param node		the parent node
	 * @return list of children nodes
	 */
	private static LinkedList<Node> expandNode(Node node) {
		LinkedList<Node> sucessor = new LinkedList<Node>(); // Successor list to keep child nodes
		
		node.getState().getKnight().nextMoves().forEach((direction,point)-> {
			// new state
			State aState = new State(node.getState());
			// move the knight position in the given direction
			aState.getKnight().moveKnight(direction);
			// see if we collided with a pawn, ie try remove knight position from pawn position list
			aState.getPawns().remove(new Pawn(aState.getKnight().getPosition(), null));
			//move pawns in alternative direction
			aState.getPawns().forEach((pawn)->{
	    		pawn.movePawn();
	    	});
			// see if a pawn collided with us, ie try remove knight position from pawn position list
			aState.getPawns().remove(new Pawn(aState.getKnight().getPosition(), null));
			
			// new node
			Node aNode = new Node(aState);
			// old node is parent node
			aNode.setParent(node);
			// add the cost
			aNode.setPathCost(node.getPathCost()+1);
			// what direction with we take to get here
			aNode.setAction(direction);
			
			// add the new node
			sucessor.add(aNode);
 		});
		return sucessor;
	}
}