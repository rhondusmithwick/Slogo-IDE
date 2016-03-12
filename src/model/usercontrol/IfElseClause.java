package model.usercontrol;

import controller.slogoparser.ExpressionTree;
import model.treenode.CommandNode;
import model.treenode.TreeNode;

import java.util.List;

public class IfElseClause extends CommandNode {

    private Double value = null;

    @Override
    protected double execute() {
        runChildren();
        return (value != null) ? value : 0;
    }

    public boolean getBoolean() {
        int expr = (int) getChildren().get(0).getValue();
        getChildren().remove(0);
        return expr == 1;
    }

    public void handleSpecific(ExpressionTree tree) {
        TreeNode node = tree.createRoot();
        this.addChild(node);
        List<List<TreeNode>> nRoots = tree.getMultipleCommandsList(2);
        if (getBoolean()) {
            nRoots.get(0).stream().forEach(this::addChild);
        } else {
            nRoots.get(1).stream().forEach(this::addChild);
        }
    }

    @Override
    protected int getNumChildrenRequired() {
        return 3;
    }
}
