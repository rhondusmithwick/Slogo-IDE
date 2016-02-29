package Model.Turn;

import Model.Action.TurnAction;
import Model.Action.TurtleAction;
import Model.TreeNode.TurtleCommandNode;
import Model.Turtle.Turtle;

/**
 * Created by rhondusmithwick on 2/27/16.
 *
 * @author Rhondu Smithwick
 */
public abstract class Turn extends TurtleCommandNode {
	
    protected double turn(int direction) {
        double degrees = getChildren().get(0).getValue();
        Turtle myTurtle = getTurtle();
        TurtleAction action = new TurnAction(myTurtle, degrees, direction);
        addAction(action);
        return degrees;
    }

    @Override
    public int getNumChildrenRequired() {
        return 1;
    }
}
