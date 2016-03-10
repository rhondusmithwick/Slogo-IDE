package model.queries;

import model.treenode.TurtleCommandNode;
import model.turtle.Turtle;

public class IsShowing extends TurtleCommandNode {


    @Override
    public double turtleExecute(Turtle turtle) {
        boolean isShowing = turtle.getTurtleProperties().getVisible();
        return isShowing ? 1 : 0;
    }

}
