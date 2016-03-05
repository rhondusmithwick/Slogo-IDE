package model.queries;

import model.action.QueryAction;
import model.action.TurtleAction;
import model.treenode.TurtleCommandNode;
import model.turtle.Turtle;

public class GetPenDown extends TurtleCommandNode {

	@Override
	protected double execute() {
		boolean isPenDown = getTurtle().getTurtleProperties().getPenDown();
		return isPenDown ? 1 : 0;
	}

}
