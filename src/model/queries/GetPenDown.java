package model.queries;

import model.treenode.TurtleCommandNode;

public class GetPenDown extends TurtleCommandNode {

    @Override
    protected double execute() {
        boolean isPenDown = getTurtle().getTurtleProperties().getPenDown();
        return isPenDown ? 1 : 0;
    }

}
