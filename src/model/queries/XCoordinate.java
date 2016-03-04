package model.queries;

import model.action.QueryAction;
import model.action.TurtleAction;
import model.turtle.Turtle;

public class XCoordinate extends Query {

	@Override
	protected double execute() {
		return getQuery();
	}
	
	protected double getQuery() {
		Turtle myTurtle = getTurtle();
		
		double x = myTurtle.getTurtleProperties().getLocation().getX();
		
		double distance = 0;
		
		TurtleAction action = new QueryAction(myTurtle, x);
		addAction(action);
		
		return distance;
	}
		

}
