package model.display;

import controller.slogoparser.ExpressionTree;
import model.treenode.TurtleCommandNode;
import model.treenode.TreeNode;
import model.turtle.Turtle;
import observables.MapObservable;

public class SetPenColor extends TurtleCommandNode {
	
	private int index;
	private MapObservable<Integer, String> colorMap;
	private Turtle turtle;

	@Override
	protected double execute() {
		setPenColor();
		return index;
	}
	
	public void handleSpecific(ExpressionTree tree) {
		TreeNode node = tree.createRoot();
		this.addChild(node);
		this.colorMap = tree.getColorMap();
		this.turtle = tree.getMyTurtle();
	}
	
	private void setPenColor() {
		index = (int) getChildren().get(0).getValue();
		turtle.getTurtleProperties().setPenColor(colorMap.get(index));
	}

}
