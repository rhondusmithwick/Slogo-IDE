package model.queries;

import model.treenode.TurtleCommandNode;
import model.turtle.Turtle;

public class YCoordinate extends TurtleCommandNode {
	
    @Override
    public double turtleExecute(Turtle turtle) {
        double y = turtle.getTurtleProperties().getLocation().getY();
        double homeY = turtle.getTurtleProperties().getHome().getY();
        return y - homeY;
    }
}
