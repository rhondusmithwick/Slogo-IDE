package model.turtleboolean;

import model.treenode.CommandNode;

public abstract class TurtleBoolean extends CommandNode {

    protected abstract double conditional();

    @Override
    protected int getNumChildrenRequired() {
        return 2;
    }

    @Override
    protected double execute() {
        return conditional();
    }
}
