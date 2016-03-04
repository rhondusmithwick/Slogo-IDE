package model.position;

import model.action.PositionAction;
import model.action.TurtleAction;
import model.treenode.TurtleCommandNode;
import model.turtle.Turtle;

public abstract class Position extends TurtleCommandNode {
	
	public double changePosition() {
		Turtle myTurtle = getTurtle();
		double x = getChildren().get(0).getValue();
		double y = getChildren().get(1).getValue();
		
		//Change distance formula. 
		
		double distance = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
		
		System.out.println(distance);
		double posX = myTurtle.getTurtleProperties().getLocation().getX();
		double posY = myTurtle.getTurtleProperties().getLocation().getY();
		
		System.out.println(posX + ", " + posY);
				
		TurtleAction action = new PositionAction(myTurtle, x, y);
		addAction(action);
		
		return distance;
	}
	
	@Override
	public int getNumChildrenRequired() {
		return 2;
	}

}
