package model.display;

import model.treenode.TurtleCommandNode;

public class GetShape extends TurtleCommandNode {

	@Override
	protected double execute() {
		return getTurtle().getTurtleProperties().getPenShapeIndex();
	}

}
