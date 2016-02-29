package Model.ClearScreen;

import Model.Action.ScreenAction;
import Model.Action.TurtleAction;
import Model.TreeNode.TurtleCommandNode;
import Model.Turtle.Turtle;
import javafx.geometry.Point2D;


public class ClearScreen extends TurtleCommandNode {

	@Override
	protected double execute() {
		return clear();
	}
	
	private double clear() {
		Turtle myTurtle = getTurtle();
		Point2D location = myTurtle.getTurtleProperties().getLocation();
		Point2D home = myTurtle.getTurtleProperties().getHome();
		double distance = location.distance(home);
		TurtleAction action = new ScreenAction(myTurtle);
		addAction(action);
		return distance;
	}
}
