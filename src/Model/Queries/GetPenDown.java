package Model.Queries;

import Model.Action.QueryAction;
import Model.Action.TurtleAction;
import Model.Turtle.Turtle;

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
