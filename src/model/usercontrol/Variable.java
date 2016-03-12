package model.usercontrol;

import model.treenode.ConstantNode;
import model.treenode.TreeNode;

/**
 * Created by rhondusmithwick on 2/28/16.
 *
 * @author Rhondu Smithwick
 */
public class Variable extends TreeNode {

    private static final String DEFAULT_STRING = "value not yet visible";

    private Double value = null;

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
            return DEFAULT_STRING;
        }
        return value.toString();
    }

    protected Double getVal() {
        return value;
    }

    public ConstantNode getConstantNode() {
        return new ConstantNode(getValue());
    }
}
