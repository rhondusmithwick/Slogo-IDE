package model.position;

import javafx.geometry.Point2D;
import model.turtle.Turtle;

public class SetXY extends Position {

    @Override
    public double turtleExecute(Turtle turtle) {
        Point2D home = turtle.getTurtleProperties().getHome();
        double x = getChildren().get(0).getValue() + home.getX();
        double y = getChildren().get(1).getValue() + home.getY();
        return changePosition(turtle, x, y);
    }

}
