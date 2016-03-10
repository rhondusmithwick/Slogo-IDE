package model.queries;

import model.treenode.TurtleCommandNode;
import model.turtle.Turtle;

public class GetPenDown extends TurtleCommandNode {

    @Override
    public double turtleExecute(Turtle turtle) {
        boolean isPenDown = turtle.getTurtleProperties().getPenDown();
        return isPenDown ? 1 : 0;
    }

}
