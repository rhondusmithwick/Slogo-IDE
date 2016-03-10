package model.usercontrol;

import controller.slogoparser.ExpressionTree;
import model.treenode.TreeNode;

import java.util.Map.Entry;

/**
 * Created by rhondusmithwick on 2/28/16.
 *
 * @author Rhondu Smithwick
 */
public class MakeVariable extends Variable {

    @Override
    public double getValue() {
        if (getVal() == null) {
           setValue(getChildren().get(0).getValue());
        }
        return super.getValue();
    }

    @Override
    protected int getNumChildrenRequired() {
        return 2;
    }

    @Override
    public void handleSpecific(ExpressionTree tree) {
        Entry<String, String> curr = tree.getParsedText().poll();
        tree.getVariables().put(curr.getValue(), this);
    }
}
