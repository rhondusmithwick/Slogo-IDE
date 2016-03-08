package model.usercontrol;

import model.treenode.TreeNode;

/**
 * Created by rhondusmithwick on 2/28/16.
 *
 * @author Rhondu Smithwick
 */
public class Variable extends TreeNode {

    protected Double value = null;

    @Override
    public double getValue() {
        return (value != null) ? value : 0.0;
    }


    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        if (value == null) {
            return "value not yet visible";
        }
        return String.format("Variable with value:  %s", value.toString());
    }

    protected Double getVal() {
        return value;
    }

}
