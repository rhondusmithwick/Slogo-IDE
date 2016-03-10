package model.turn;

import model.treenode.TurtleCommandNode;
import model.turtle.Turtle;

/**
 * Created by rhondusmithwick on 2/27/16.
 *
 * @author Rhondu Smithwick
 */
public abstract class Turn extends TurtleCommandNode {

    protected double turn(Turtle turtle, int direction) {
        double degrees = getChildren().get(0).getValue();
        double currAngle = turtle.getTurtleProperties().getHeading();
        double newAngle = (direction == 0) ? degrees : (currAngle + (direction * degrees));
        turtle.getTurtleProperties().setHeading(newAngle);
        return degrees;
    }

    @Override
    public int getNumChildrenRequired() {
        return 1;
    }
}
