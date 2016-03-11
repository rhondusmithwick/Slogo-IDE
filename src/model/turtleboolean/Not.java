package model.turtleboolean;

import model.treenode.CommandNode;

public class Not extends CommandNode {

    @Override
    protected double execute() {
        double value1 = getChildren().get(0).getValue();
        return value1 == 0 ? 1 : 0;
    }

    @Override
    protected int getNumChildrenRequired() {
        return 1;
    }
}
