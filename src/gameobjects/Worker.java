package gameobjects;

import java.util.List;
import java.awt.Point;
import java.util.LinkedList;

import javax.swing.SwingWorker;

import artificialintelligence.Node;
import artificialintelligence.Search;
import game.Application;

public class Worker extends SwingWorker<Boolean, Point> {
	private Application app;
	
	public Worker(Application app){
		this.app = app;
	}
	
	@Override
	protected Boolean doInBackground() throws Exception {
		// to store search node
       	Node node = null;
       	// to store action list
       	LinkedList<DirectionEnum> actions;

		// apply the search
       	switch(this.app.getView().getTypeList().getSelectedValue()) {
 			case BFS:
 		     	node = Search.bfsSearch(this.app.getModel());
			break;
 			case DFS:
 		     	node = Search.dfsSearch(this.app.getModel());
			break;
 			case ASTAREUCLIDEAN:
 				node = Search.AStarSearch(this.app.getModel(), GameTypeEnum.ASTAREUCLIDEAN);
 				break;
 			case ASTARMANHATTEN:
 				node = Search.AStarSearch(this.app.getModel(), GameTypeEnum.ASTARMANHATTEN);
 				break;
 			case ASTAREUCLIDEANMANHATTEN:
 				node = Search.AStarSearch(this.app.getModel(), GameTypeEnum.ASTAREUCLIDEANMANHATTEN);
 				break;
		default:
			// do nothing
			break;
 		}
       	
       	// show stat
       	this.app.getView().getStatField().setText(this.app.getModel().getStats());
       	
       	// gather actions
     	actions = new LinkedList<DirectionEnum>();
		while(node!=null){
			// save the action if there is one
			if(node.getAction()!=null)
				actions.addFirst(node.getAction());
			node = node.getParent(); // get the parent
		}
		while(!actions.isEmpty()){
			DirectionEnum action = actions.removeFirst();
			Point currPosition = this.app.getModel().getKnight().getPosition();
			
			// get coordinate from knights current position
			Point coordinate = DirectionEnum.getPointInDirection(currPosition, action);
			
			// publish and wait
			this.publish(coordinate);
            Thread.sleep(this.app.getModel().getFrameRate());
		
			//TODO: System.out.println(action);
		}
		this.app.getView().getStartStopButton().doClick(); // end game
 		
		return true;
	}
	
	@Override
	protected void process(List<Point> chunks) {
		// make the move(s)
		chunks.forEach((coordinate)->{
			this.app.getView().getBoard().getSquareButtons()[coordinate.x][coordinate.y].doClick();
		});
	}
}
