package model.turn;

import javafx.geometry.Point2D;
import model.treenode.TurtleCommandNode;

public class Towards extends TurtleCommandNode {

    @Override
    protected double execute() {
        return turn();
    }

    protected double turn() {
        Point2D location = getTurtle().getTurtleProperties().getLocation();
        Point2D home = getTurtle().getTurtleProperties().getHome();

        double x = home.getX() + getChildren().get(0).getValue();
        double y = home.getY() + getChildren().get(1).getValue();

        double degrees = Math.toDegrees(Math.atan2(y - location.getY(), x - location.getX()));
        getTurtle().getTurtleProperties().setHeading(degrees);
        return degrees;
    }

    @Override
    public int getNumChildrenRequired() {
        return 2;
    }
}
