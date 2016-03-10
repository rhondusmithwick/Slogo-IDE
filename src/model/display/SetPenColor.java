package model.display;

import controller.slogoparser.ExpressionTree;
import maps.IndexMap;
import model.treenode.TurtleCommandNode;
import model.treenode.TreeNode;
import model.turtle.Turtle;
import observables.MapObservable;

public class SetPenColor extends TurtleCommandNode {
	
	private int index;
	private IndexMap colorMap;

	@Override
	public double turtleExecute(Turtle turtle) {
		setPenColor(turtle);
		return index;
	}


	public void handleSpecific(ExpressionTree tree) {
		TreeNode node = tree.createRoot();
		this.addChild(node);
		this.colorMap = tree.getColorMap();
		System.out.println(colorMap.getIndexMap().getStringProperty().get());
	}

	private void setPenColor(Turtle turtle) {
		index = (int) getChildren().get(0).getValue();
		turtle.getTurtleProperties().setPenColor(colorMap.get(index));
	}

}
