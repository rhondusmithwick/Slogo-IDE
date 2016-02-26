package Model.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rhondusmithwick on 2/25/16.
 *
 * @author Rhondu Smithwick
 */
public abstract class TreeNode {

    private final List<TreeNode> children = new ArrayList<>();

    public boolean hasTurtle() {
        return false;
    }

    public double getValue() {
        return 0.0;
    }

    public void addChild(TreeNode n) {
        children.add(n);
    }

    protected List<TreeNode> getChildren() {
        return children;
    }

    protected int getNumChildren() {
        return 0;
    }

    public boolean needsMoreChildren() {
        return (hasTurtle() && childrenCheck(1)
                || (!hasTurtle() && childrenCheck(0)));
    }

    private boolean childrenCheck(int offset) {
        return (children.size() - offset) < getNumChildren();
    }

    public String toString() {
        return String.format("%s: %s", getClass().getSimpleName(), children);
    }
}
