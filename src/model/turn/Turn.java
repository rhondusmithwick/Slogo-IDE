package model.turn;

import model.action.TurnAction;
import model.action.TurtleAction;
import model.treenode.TurtleCommandNode;
import model.turtle.Turtle;

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
