package Model.Turn;

import Model.TreeNode.TurtleCommandNode;

/**
 * Created by rhondusmithwick on 2/27/16.
 *
 * @author Rhondu Smithwick
 */
public class SetHeading extends TurtleCommandNode {

    public double execute() {
        double degrees = getChildren().get(1).getValue();
        getTurtle().getTurtleProperties().setHeading(degrees);
        return degrees;
    }

    @Override
    public int getNumChildrenRequired() {
        return 1;
    }
}
