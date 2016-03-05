package model.position;

import javafx.geometry.Point2D;

public class SetXY extends Position {

	@Override
	protected double execute() {
		Point2D home = getTurtle().getTurtleProperties().getHome();
		double x = getChildren().get(0).getValue() + home.getX();
		double y = getChildren().get(1).getValue() + home.getY();
		return changePosition(x, y);
	}

}
