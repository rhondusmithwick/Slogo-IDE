package Model.TreeNode;

import Model.Turtle.Turtle;

/**
 * Created by rhondusmithwick on 2/22/16.
 *
 * @author Rhondu Smithwick
 */
public abstract class TurtleCommandNode extends CommandNode {

    protected Turtle getTurtle() {
        return (Turtle) getChildren().get(0);
    }

    @Override
    public boolean needsMoreChildren() {
        int numChildren = getChildren().size() - 1;
        return (numChildren < getNumChildrenRequired());
    }

}
