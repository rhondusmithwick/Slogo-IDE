package model.queries;

import model.treenode.TurtleCommandNode;

public class IsShowing extends TurtleCommandNode {

    @Override
    protected double execute() {
        boolean isShowing = getTurtle().getTurtleProperties().getVisible();
        return isShowing ? 1 : 0;
    }
}
