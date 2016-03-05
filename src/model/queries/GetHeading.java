package model.queries;

import model.action.QueryAction;
import model.action.TurtleAction;
import model.treenode.TurtleCommandNode;
import model.turtle.Turtle;

public class GetHeading extends TurtleCommandNode {

	@Override
	protected double execute() {
		return getTurtle().getTurtleProperties().getHeading();
	}
}
