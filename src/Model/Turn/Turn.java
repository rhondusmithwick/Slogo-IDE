package Model.Turn;

import Model.TreeNode.TreeNode;
import Model.TreeNode.TurtleCommandNode;

/**
 * Created by rhondusmithwick on 2/27/16.
 *
 * @author Rhondu Smithwick
 */
public abstract class Turn extends TurtleCommandNode {

    public double turn(int direction) {
        double currAngle = getTurtle().getTurtleProperties().getHeading();
        TreeNode degreesNode = getChild();
        double degrees = degreesNode != null ? degreesNode.getValue() : 0;
        double newAngle = currAngle + (direction * degrees);
        getTurtle().getTurtleProperties().setHeading(newAngle);
        return degrees;
    }

    @Override
    public int getNumChildrenRequired() {
        return 1;
    }
}
