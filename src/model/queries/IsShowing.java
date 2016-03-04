package model.queries;

import model.action.QueryAction;
import model.action.TurtleAction;
import model.turtle.Turtle;

public class IsShowing extends Query {

	@Override
	protected double getQuery() {
		Turtle myTurtle = getTurtle();
		
		boolean isShowing = myTurtle.getTurtleProperties().getVisible();
		
		TurtleAction action = new QueryAction(myTurtle, isShowing);
		addAction(action);
		
		return isShowing ? 1 : 0;
	}

	@Override
	protected double execute() {
		return getQuery();
	}
}
