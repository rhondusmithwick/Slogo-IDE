package model.queries;

import model.action.QueryAction;
import model.action.TurtleAction;
import model.turtle.Turtle;

public class YCoordinate extends Query {

	@Override
	protected double getQuery() {
		Turtle myTurtle = getTurtle();
		
		double y = myTurtle.getTurtleProperties().getLocation().getY();
		
		double distance = 0;
		
		TurtleAction action = new QueryAction(myTurtle, y);
		addAction(action);
				
		return distance;
	}

	@Override
	protected double execute() {
		return getQuery();
	}
}
