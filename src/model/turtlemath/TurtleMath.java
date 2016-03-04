package model.turtlemath;

import model.treenode.CommandNode;

public abstract class TurtleMath extends CommandNode {
	
	public abstract double calculate();
	
	@Override
	public int getNumChildrenRequired() {
		return 1;
	}
	
	protected double execute() {
		return calculate();
	}
	
}
