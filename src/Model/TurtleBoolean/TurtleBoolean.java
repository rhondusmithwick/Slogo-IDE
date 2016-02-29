package Model.TurtleBoolean;

import Model.TreeNode.CommandNode;

public abstract class TurtleBoolean extends CommandNode {
	
	public abstract double conditional();
	
	@Override
	public int getNumChildrenRequired() {
		return 2;
	}
	
	@Override
	protected double execute() {
		return conditional();
	}
}
