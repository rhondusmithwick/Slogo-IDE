package Model.ClearScreen;

import Model.Action.ScreenAction;
import Model.Action.TurtleAction;
import Model.TreeNode.TurtleCommandNode;
import Model.Turtle.Turtle;

public class ClearScreen extends TurtleCommandNode {

	@Override
	protected double execute() {
		return clear();
	}
	
	protected double clear() {
		Turtle myTurtle = getTurtle();
		
		double distance = 0;
		
		TurtleAction action = new ScreenAction(myTurtle, myTurtle.getTurtleProperties());
		addAction(action);
		
		return distance;
	}
}
