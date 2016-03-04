package model.position;

import model.action.PositionAction;
import model.action.TurtleAction;
import model.turtle.Turtle;

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
