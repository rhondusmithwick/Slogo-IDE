package model.turn;

import javafx.geometry.Point2D;
import model.treenode.TurtleCommandNode;
import model.turtle.Turtle;

public class Towards extends TurtleCommandNode {

    @Override
    public double turtleExecute(Turtle turtle) {
        Point2D location = turtle.getTurtleProperties().getLocation();
        Point2D home = turtle.getTurtleProperties().getHome();

        double x = home.getX() + getChildren().get(0).getValue();
        double y = home.getY() + getChildren().get(1).getValue();

        double degrees = Math.toDegrees(Math.atan2(y - location.getY(), x - location.getX()));
        turtle.getTurtleProperties().setHeading(degrees);
        return degrees;
    }

    @Override
    protected int getNumChildrenRequired() {
        return 2;
    }
}
