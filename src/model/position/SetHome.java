package model.position;

import javafx.geometry.Point2D;
import model.turtle.Turtle;

public class SetHome extends Position {
    @Override
    public double turtleExecute(Turtle turtle) {
        Point2D home = turtle.getTurtleProperties().getHome();
        return changePosition(turtle, home.getX(), home.getY());
    }
}
