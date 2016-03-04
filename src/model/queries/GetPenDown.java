package model.queries;

import model.action.QueryAction;
import model.action.TurtleAction;
import model.turtle.Turtle;

public class GetPenDown extends Query {

	@Override
	protected double getQuery() {
		Turtle myTurtle = getTurtle();
		
		boolean isPenDown = myTurtle.getTurtleProperties().getPenDown();
		
		TurtleAction action = new QueryAction(myTurtle, isPenDown);
		addAction(action);
		return isPenDown ? 1 : 0;
	}

	@Override
	protected double execute() {
		return getQuery();
	}

}
