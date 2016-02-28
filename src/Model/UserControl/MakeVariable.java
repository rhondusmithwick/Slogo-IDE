package Model.UserControl;

import Model.TreeNode.TreeNode;

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
    protected int getNumChildrenRequired() {
        return 1;
    }
}
