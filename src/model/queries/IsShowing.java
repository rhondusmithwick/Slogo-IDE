package model.queries;

import model.action.QueryAction;
import model.action.TurtleAction;
import model.treenode.TurtleCommandNode;
import model.turtle.Turtle;

public class IsShowing extends TurtleCommandNode {

	@Override
	protected double execute() {
		boolean isShowing = getTurtle().getTurtleProperties().getVisible();
		return isShowing ? 1 : 0;
	}
}
