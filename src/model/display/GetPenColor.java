package model.display;

import model.treenode.TurtleCommandNode;

public class GetPenColor extends TurtleCommandNode {

	@Override
	protected double execute() {
		return getTurtle().getTurtleProperties().getPenColorIndex();
	}
	
}
