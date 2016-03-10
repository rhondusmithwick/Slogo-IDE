package model.display;

import model.treenode.TurtleCommandNode;
import model.turtle.Turtle;
import observables.MapObservable;

public class SetPenColor extends TurtleCommandNode {
	
	private int index;
	private MapObservable<Integer, String> colorMap;

	@Override
	public double turtleExecute(Turtle turtle) {
		setPenColor(turtle);
		return index;
	}
	
	@Override
	protected int getNumChildrenRequired() {
		return 1;
	}
	
	private void setPenColor(Turtle turtle) {
		this.colorMap = getTree().getColorMap();
		index = (int) getChildren().get(0).getValue();
		turtle.getTurtleProperties().setPenColorIndex(index);
		turtle.getTurtleProperties().setPenColor(colorMap.get(index));
	}

}
