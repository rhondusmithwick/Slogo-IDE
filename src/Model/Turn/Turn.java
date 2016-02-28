package Model.Turn;

import Model.TreeNode.TurtleCommandNode;
import Model.Turtle.TurnAction;
import Model.Turtle.TurtleAction;

/**
 * Created by rhondusmithwick on 2/27/16.
 *
 * @author Rhondu Smithwick
 */
public abstract class Turn extends TurtleCommandNode {

    public double turn(int direction) {
        double degrees = getChildren().get(0).getValue();
        TurtleAction action = new TurnAction(getTurtle(), degrees, direction);
        getTurtle().addAction(action);
        return degrees;
    }

    @Override
    public int getNumChildrenRequired() {
        return 1;
    }
}
