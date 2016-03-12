package model.display;

import controller.slogoparser.ExpressionTree;
import maps.IndexMap;
import model.treenode.CommandNode;
import model.treenode.TreeNode;

public class SetPalette extends CommandNode {

    private IndexMap colorMap;
    private int index;

    @Override
    protected double execute() {
        setPalette();
        return index;
    }

    @Override
    public void handleSpecific(ExpressionTree tree) {
        TreeNode node = tree.createRoot();
        this.addChild(node);
        this.colorMap = tree.getColorMap();
    }

    private void setPalette() {
        index = (int) getChildren().get(0).getValue();
        int r = (int) getChildren().get(1).getValue();
        int g = (int) getChildren().get(2).getValue();
        int b = (int) getChildren().get(3).getValue();
        String value = r + " " + g + " " + b;
        colorMap.setAtIndex(index, value);
    }

    @Override
    protected int getNumChildrenRequired() {
        return 4;
    }

}
