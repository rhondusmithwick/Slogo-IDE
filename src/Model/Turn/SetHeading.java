package Model.Turn;

import Model.TreeNode.TreeNode;
import Model.TreeNode.TurtleCommandNode;

/**
 * Created by rhondusmithwick on 2/27/16.
 *
 * @author Rhondu Smithwick
 */
public class SetHeading extends TurtleCommandNode {

    public double execute() {
        TreeNode degreesNode = getChild();
        double degrees = degreesNode != null ? degreesNode.getValue() : 0;
        getTurtle().getTurtleProperties().setHeading(degrees);
        return degrees;
    }

    @Override
    public int getNumChildrenRequired() {
        return 1;
    }
}
