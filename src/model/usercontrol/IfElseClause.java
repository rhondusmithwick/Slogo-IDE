package model.usercontrol;

import model.treenode.CommandNode;
import model.treenode.TreeNode;

public class IfElseClause extends CommandNode {
	
	private Double value = null;
	
	@Override
	protected double execute() {
		runChildren();
		return (value != null) ? value : 0;
	}

	public boolean getBoolean() {
		int expr = (int) getChildren().get(0).getValue();
		getChildren().remove(0);
		return (expr == 1) ? true : false;
	}
	
	private void runChildren() {
		value = getChildren().stream().map(TreeNode::getValue).reduce((a, b) -> b).orElse(null);
		getChildren().stream().forEach(i -> System.out.println(i));
	}
}
