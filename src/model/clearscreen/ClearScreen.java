package model.clearscreen;

import javafx.geometry.Point2D;
import model.action.ScreenAction;
import model.action.TurtleAction;
import model.treenode.TurtleCommandNode;
import model.turtle.Turtle;


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
