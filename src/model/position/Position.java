package model.position;

import javafx.geometry.Point2D;
import model.treenode.TurtleCommandNode;

public abstract class Position extends TurtleCommandNode {

    protected double changePosition(double x, double y) {
        Point2D pointToMoveTo = new Point2D(x, y);
        Point2D location = getTurtle().getTurtleProperties().getLocation();
        double distance = location.distance(pointToMoveTo);
        getTurtle().getTurtleProperties().setLocation(pointToMoveTo);
        return distance;
    }

    @Override
    public int getNumChildrenRequired() {
        return 2;
    }

}
