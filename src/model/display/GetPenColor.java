package model.display;

import model.treenode.TurtleCommandNode;
import model.turtle.Turtle;

public class GetPenColor extends TurtleCommandNode {

	@Override
	public double turtleExecute(Turtle turtle) {
		return turtle.getTurtleProperties().getPenColorIndex();
	}
	
}
