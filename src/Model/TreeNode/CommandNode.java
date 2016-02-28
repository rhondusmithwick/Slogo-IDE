package Model.TreeNode;


/**
 * Created by rhondusmithwick on 2/25/16.
 *
 * @author Rhondu Smithwick
 */
public abstract class CommandNode extends TreeNode {

    @Override
    public double getValue() {
        return execute();
    }

    protected abstract double execute();
}
