package model.queries;

import model.treenode.TurtleCommandNode;

public class XCoordinate extends TurtleCommandNode {

    @Override
    protected double execute() {
        double x = getTurtle().getTurtleProperties().getLocation().getX();
        double homeX = getTurtle().getTurtleProperties().getHome().getX();
        return x - homeX;
    }


}
