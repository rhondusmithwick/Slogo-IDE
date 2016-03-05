package model.queries;

import model.action.QueryAction;
import model.action.TurtleAction;
import model.turtle.Turtle;

public class GetHeading extends Query {

	@Override
	protected double getQuery() {
		Turtle myTurtle = getTurtle();

		double heading = myTurtle.getTurtleProperties().getHeading();

		TurtleAction action = new QueryAction(myTurtle, heading);
		addAction(action);

		return heading;
	}

	@Override
	protected double execute() {
		return getQuery();
	}
}
