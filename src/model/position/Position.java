package model.position;

import javafx.geometry.Point2D;
import model.treenode.TurtleCommandNode;
import model.turtle.Turtle;

public abstract class Position extends TurtleCommandNode {

    protected double changePosition(Turtle turtle, double x, double y) {
        Point2D pointToMoveTo = new Point2D(x, y);
        Point2D location = turtle.getTurtleProperties().getLocation();
        double distance = location.distance(pointToMoveTo);
        turtle.getTurtleProperties().setLocation(pointToMoveTo);
        return distance;
    }

    @Override
    protected int getNumChildrenRequired() {
        return 2;
    }

}
