package Model.Position;

import Model.Action.PositionAction;
import Model.Action.TurtleAction;
import Model.Turtle.Turtle;

public class SetHome extends Position {
	
	@Override
	public double changePosition() {
		Turtle myTurtle = getTurtle();
		double x = 0;
		double y = 0;
		
		double distance = 0;
		
		TurtleAction action = new PositionAction(myTurtle, x, y);
		addAction(action);
		
		return distance;
	}

	@Override
	protected double execute() {
		return changePosition();
	}
	
}
