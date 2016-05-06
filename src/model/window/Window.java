package model.window;

import javafx.geometry.Point2D;
import model.turtle.Turtle;

public interface Window {
	
	public Point2D enforceWindow(Turtle turtle, double newX, double newY);
}
