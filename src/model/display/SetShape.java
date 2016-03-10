package model.display;

import model.treenode.TurtleCommandNode;
import model.turtle.Turtle;
import observables.MapObservable;

public class SetShape extends TurtleCommandNode {
	
	private int index;
	private MapObservable<Integer, String> imageMap;

	@Override
	public double turtleExecute(Turtle turtle) {
		setShape(turtle);
		return index;
	}
	
	@Override
	protected int getNumChildrenRequired() {
		return 1;
	}
	
	private void setShape(Turtle turtle) {
		this.imageMap = getTree().getImageMap();
		index = (int) getChildren().get(0).getValue();
		turtle.getTurtleProperties().setPenShapeIndex(index);
		turtle.getTurtleProperties().setImage(imageMap.get(index));
	}

}
