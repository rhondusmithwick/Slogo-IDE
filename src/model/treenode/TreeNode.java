package model.treenode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rhondusmithwick on 2/25/16.
 *
 * @author Rhondu Smithwick
 */
public abstract class TreeNode {

    private final List<TreeNode> children = new ArrayList<>();

    public double getValue() {
        return 0.0;
    }

    public void addChild(TreeNode n) {
        children.add(n);
    }

    protected List<TreeNode> getChildren() {
        return children;
    }

    public boolean needsMoreChildren() {
        int numChildren = children.size();
        return (numChildren < getNumChildrenRequired());
    }

    protected int getNumChildrenRequired() {
        return 0;
    }

    @Override
    public String toString() {
        return String.format("%s: %s", getClass().getSimpleName(), children);
    }
}
