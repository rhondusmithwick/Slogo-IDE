package model.usercontrol;

import controller.slogoparser.ExpressionTree;
import model.treenode.TreeNode;

import java.util.Map.Entry;

/**
 * Created by rhondusmithwick on 2/28/16.
 *
 * @author Rhondu Smithwick
 */
public class MakeVariable extends TreeNode {

    private Double value = null;

    @Override
    public double getValue() {
        if (value == null) {
            value = getChildren().get(0).getValue();
        }
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    protected int getNumChildrenRequired() {
        return 2;
    }

    @Override
    public String toString() {
        if (value == null) {
            return "value not yet visible";
        }
        return value.toString();
    }

    @Override
    public void handleSpecific(ExpressionTree tree) {
        Entry<String, String> curr = tree.getParsedText().poll();
        tree.getVariables().put(curr.getValue(), this);
    }
}
