package model.display;

import controller.slogoparser.ExpressionTree;
import model.treenode.CommandNode;
import model.treenode.TreeNode;
import observables.ObjectObservable;

public class SetBackground extends CommandNode {
	
	private ObjectObservable<String> bgColor;
	private int index;

	@Override
	protected double execute() {
		setBackground();
		return index;
	}
	
	public void handleSpecific(ExpressionTree tree) {
		TreeNode node = tree.createRoot();
		this.addChild(node);
	}
	
	private void setBackground() {
		index = (int) getChildren().get(0).getValue();
		System.out.println("reached1");
		bgColor.set("blue");
	}
	
	@Override
	public int getNumChildrenRequired() {
		return 1;
	}

}
