package Model.Queries;

import Model.Action.QueryAction;
import Model.Action.TurtleAction;
import Model.Turtle.Turtle;

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
