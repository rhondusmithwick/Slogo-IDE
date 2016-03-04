package model.action;

import model.turtle.Turtle;

/**
 * Created by rhondusmithwick on 2/27/16.
 *
 * @author Rhondu Smithwick
 */
public class PenAction extends TurtleAction {

    private final boolean penDown;

    public PenAction(Turtle myTurtle, boolean penDown) {
        super(myTurtle);
        this.penDown = penDown;
    }

    @Override
    public void run() {
        getMyTurtle().getTurtleProperties().setPenDown(penDown);
        super.run();
    }
}
