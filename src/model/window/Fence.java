package model.window;

import model.treenode.TurtleCommandNode;
import model.turtle.Turtle;

public class Fence extends TurtleCommandNode {

	@Override
	protected double turtleExecute(Turtle turtle) {
		turtle.getTurtleProperties().setWindow(new FenceWindow());
		return 3;
	}

}
