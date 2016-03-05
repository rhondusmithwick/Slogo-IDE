package model.queries;

import model.action.QueryAction;
import model.action.TurtleAction;
import model.treenode.TurtleCommandNode;
import model.turtle.Turtle;

public class YCoordinate extends TurtleCommandNode {

	@Override
	protected double execute() {
		double y = getTurtle().getTurtleProperties().getLocation().getY();
		double homeY = getTurtle().getTurtleProperties().getHome().getY();
		return y - homeY;
	}
}
