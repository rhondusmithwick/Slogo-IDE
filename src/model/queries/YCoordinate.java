package model.queries;

import model.treenode.TurtleCommandNode;

public class YCoordinate extends TurtleCommandNode {

    @Override
    protected double execute() {
        double y = getTurtle().getTurtleProperties().getLocation().getY();
        double homeY = getTurtle().getTurtleProperties().getHome().getY();
        return y - homeY;
    }
}
