package Model.TurtleMath;

import Model.TreeNode.TurtleCommandNode;

public abstract class TurtleMath extends TurtleCommandNode {
	
	public abstract double calculate();
	
	@Override
	public int getNumChildrenRequired() {
		return 1;
	}
	
}
