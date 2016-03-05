package model.queries;

import model.action.QueryAction;
import model.action.TurtleAction;
import model.treenode.TurtleCommandNode;
import model.turtle.Turtle;

public class XCoordinate extends TurtleCommandNode {

	@Override
	protected double execute() {
		double x = getTurtle().getTurtleProperties().getLocation().getX();
		double homeX = getTurtle().getTurtleProperties().getHome().getX();
		return x - homeX;
	}
		

}
