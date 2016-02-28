package Model.Action;

import Model.Turtle.Turtle;

/**
 * Created by rhondusmithwick on 2/27/16.
 *
 * @author Rhondu Smithwick
 */
public abstract class TurtleAction implements Runnable {

    private volatile boolean isDone = false;

    private final Turtle myTurtle;

    public TurtleAction(Turtle myTurtle) {
        this.myTurtle = myTurtle;
    }

    public synchronized boolean isDone() {
        return isDone;
    }

    @Override
    public void run() {
        isDone = true;
    }

    public Turtle getMyTurtle() {
        return myTurtle;
    }

}
