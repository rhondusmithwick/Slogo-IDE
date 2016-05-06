package model.window;

import model.treenode.TurtleCommandNode;
import model.turtle.Turtle;

public class Continuous extends TurtleCommandNode {

	@Override
	protected double turtleExecute(Turtle turtle) {
		turtle.getTurtleProperties().setWindow(new ContinuousWindow());
		return 1;
	}
	
}
