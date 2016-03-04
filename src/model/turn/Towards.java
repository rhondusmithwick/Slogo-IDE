package model.turn;

import model.action.TurnAction;
import model.action.TurtleAction;
import model.treenode.TurtleCommandNode;
import model.turtle.Turtle;

public class Towards extends TurtleCommandNode {
	
	@Override
	protected double execute() {	
		return turn();
	}
	
	protected double turn() {
		Turtle myTurtle = getTurtle();
		
		double currentX = myTurtle.getTurtleProperties().getLocation().getX();
		double currentY = myTurtle.getTurtleProperties().getLocation().getY();
		
		double x = currentX + getChildren().get(0).getValue();
		double y = currentY + getChildren().get(1).getValue();
		
		double degrees = Math.toDegrees(Math.atan2(y - currentY, x - currentX));
		
		TurtleAction action = new TurnAction(myTurtle, degrees, 0);
		addAction(action);
		
		return degrees;
	}
	
	@Override
	public int getNumChildrenRequired() {
		return 2;
	}
}
