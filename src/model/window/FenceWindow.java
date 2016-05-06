package model.window;

import javafx.geometry.Point2D;
import model.turtle.Turtle;

public class FenceWindow implements Window {

	@Override
	public Point2D enforceWindow(Turtle turtle, double newX, double newY) {
		double turtleWindowX = turtle.getTurtleProperties().getHome().getX()*2;
		double turtleWindowY = turtle.getTurtleProperties().getHome().getY()*2;
		
		if (newX < 0) {
			newX = 0;
		} else if (newX > turtleWindowX) {
			newX = turtleWindowX;
		}
		
		if (newY < 0) {
			newY = 0;
		} else if (newY > turtleWindowY) {
			newY = turtleWindowY;
		}
			
		return new Point2D(newX, newY);
	}

}
