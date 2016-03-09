package model.usercontrol;

import java.util.List;

import controller.slogoparser.ExpressionTree;
import model.treenode.CommandNode;
import model.treenode.TreeNode;

public class IfClause extends CommandNode {
	
	private boolean bool = false;
	private Double value = null;

	@Override
	protected double execute() {
		getBoolean();
		if (bool) {
			runChildren();
		}
		return (value != null) ? value : 0;
	}
	
	private void getBoolean() {
		int expr = (int) getChildren().get(0).getValue();
		getChildren().remove(0);
		bool = (expr == 1) ? true : false;
	}
	
	private void runChildren() {
		value = getChildren().stream().map(TreeNode::getValue).reduce((a, b) -> b).orElse(null);
		getChildren().stream().forEach(i -> System.out.println(i));
	}
	
	public void handleSpecific(ExpressionTree tree) {
		TreeNode node = tree.createRoot();
		this.addChild(node);
		List<TreeNode> nRoots = tree.getCommandsFromList();
		nRoots.stream().forEach(this::addChild);
	}
	
	@Override
	public int getNumChildrenRequired() {
		return 2;
	}
}
