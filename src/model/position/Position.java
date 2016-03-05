package model.position;

import javafx.geometry.Point2D;
import model.action.PositionAction;
import model.action.TurtleAction;
import model.treenode.TurtleCommandNode;
import model.turtle.Turtle;

public abstract class Position extends TurtleCommandNode {
	
	protected double changePosition() {
		Point2D home = getTurtle().getTurtleProperties().getHome();
		double x = getChildren().get(0).getValue() + home.getX();
		double y = getChildren().get(1).getValue() + home.getY();
		Point2D pointToMoveTo = new Point2D(x, y);
		Point2D location = getTurtle().getTurtleProperties().getLocation();
		double distance = location.distance(pointToMoveTo);
		getTurtle().getTurtleProperties().setLocation(pointToMoveTo);
		return distance;
	}
	
	@Override
	public int getNumChildrenRequired() {
		return 2;
	}

}
