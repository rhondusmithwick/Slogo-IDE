package model.display;

import controller.slogoparser.ExpressionTree;
import model.treenode.TurtleCommandNode;
import model.treenode.TreeNode;
import model.turtle.Turtle;
import observables.MapObservable;

public class SetShape extends TurtleCommandNode {
	
	private int index;
	private Turtle turtle;
	private MapObservable<Integer, String> imageMap;

	@Override
	protected double execute() {
		setShape();
		return index;
	}
	
	public void handleSpecific(ExpressionTree tree) {
		TreeNode node = tree.createRoot();
		this.addChild(node);
		this.turtle = tree.getMyTurtle();
		this.imageMap = tree.getImageMap();
	}
	
	private void setShape() {
		index = (int) getChildren().get(0).getValue();
		turtle.getTurtleProperties().setImage(imageMap.get(index));
	}

}
