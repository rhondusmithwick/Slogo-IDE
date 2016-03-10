package model.display;

import controller.slogoparser.ExpressionTree;
import model.treenode.TreeNode;
import model.treenode.TurtleCommandNode;
import model.turtle.Turtle;

public class SetPenSize extends TurtleCommandNode {
	
	private double pixels;
	private Turtle turtle;

	@Override
	protected double execute() {
		setPenSize();
		return pixels;
	}
	
	public void handleSpecific(ExpressionTree tree) {
		TreeNode node = tree.createRoot();
		this.addChild(node);
		this.turtle = tree.getMyTurtle();
	}
	
	private void setPenSize() {
		pixels = getChildren().get(0).getValue();
		turtle.getTurtleProperties().setPenSize(pixels);
	}

}
