package model.window;

import javafx.geometry.Point2D;
import model.turtle.Turtle;

public class ContinuousWindow implements Window {
	
	@Override
	public Point2D enforceWindow(Turtle turtle, double newX, double newY) {
		return new Point2D(newX, newY);
	}
	

}
