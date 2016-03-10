package model.display;

import controller.slogoparser.ExpressionTree;
import model.treenode.CommandNode;
import model.treenode.TreeNode;
import observables.MapObservable;
import observables.ObjectObservable;

public class SetBackground extends CommandNode {
	
	private int index;
	private MapObservable<Integer, String> colorMap;
	private ObjectObservable<String> backgroundColor;
	
	@Override
	protected double execute() {
		setBackground();
		return index;
	}
	
	public void handleSpecific(ExpressionTree tree) {
		TreeNode node = tree.createRoot();
		this.addChild(node);
		this.colorMap = tree.getColorMap();
		this.backgroundColor = tree.getBackgroundColor();
	}
	
	private void setBackground() {
		index = (int) getChildren().get(0).getValue();
		backgroundColor.set(colorMap.get(index));
	}
	
	@Override
	protected int getNumChildrenRequired() {
		return 1;
	}

}
