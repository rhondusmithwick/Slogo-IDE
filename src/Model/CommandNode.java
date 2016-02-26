package Model;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by rhondusmithwick on 2/25/16.
 *
 * @author Rhondu Smithwick
 */
public abstract class CommandNode implements TreeNode, Command {

    private final List<TreeNode> children = new ArrayList<>();

    @Override
    public void addChild(TreeNode n) {
        children.add(n);
    }

    @Override
    public double getValue() {
        return execute();
    }

    @Override
    public List<TreeNode> getChildren() {
        return children;
    }

}
