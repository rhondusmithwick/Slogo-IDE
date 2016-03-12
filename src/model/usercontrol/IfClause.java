package model.usercontrol;

import controller.slogoparser.ExpressionTree;
import model.treenode.CommandNode;
import model.treenode.TreeNode;

import java.util.List;

public class IfClause extends CommandNode {

    private Double value = null;

    @Override
    protected double execute() {
        if (getBoolean()) {
            runChildren();
        }
        return (value != null) ? value : 0;
    }

    private boolean getBoolean() {
        int expr = (int) getChildren().get(0).getValue();
        getChildren().remove(0);
        return expr == 1;
    }

    public void handleSpecific(ExpressionTree tree) {
        TreeNode node = tree.createRoot();
        this.addChild(node);
        List<TreeNode> nRoots = tree.getCommandsFromList();
        nRoots.stream().forEach(this::addChild);
    }

    @Override
    protected int getNumChildrenRequired() {
        return 2;
    }
}
