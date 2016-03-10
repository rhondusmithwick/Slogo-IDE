package model.queries;

import model.treenode.TurtleCommandNode;
import model.turtle.Turtle;

public class XCoordinate extends TurtleCommandNode {
    @Override
    public double turtleExecute(Turtle turtle) {
        double x = turtle.getTurtleProperties().getLocation().getX();
        double homeX = turtle.getTurtleProperties().getHome().getX();
        return x - homeX;
    }

}
