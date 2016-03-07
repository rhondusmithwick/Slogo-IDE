package model.queries;

import model.treenode.TurtleCommandNode;

public class GetHeading extends TurtleCommandNode {

    @Override
    protected double execute() {
        return getTurtle().getTurtleProperties().getHeading();
    }
}
