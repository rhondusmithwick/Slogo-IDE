package model.oneoperatormath;

import model.treenode.CommandNode;

public class Pi extends CommandNode {
    @Override
    protected double execute() {
        return Math.PI;
    }
}
