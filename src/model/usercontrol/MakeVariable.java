package model.usercontrol;

import model.treenode.TreeNode;

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

    @Override
    public int getNumChildrenRequired() {
        return 1;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        if (value == null) {
            return "value not yet visible";
        }
        return value.toString();
    }
}
