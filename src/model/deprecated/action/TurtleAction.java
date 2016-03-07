package model.deprecated.action;

import model.turtle.Turtle;

/**
 * Created by rhondusmithwick on 2/27/16.
 *
 * @author Rhondu Smithwick
 */
public abstract class TurtleAction implements Runnable {

    private final Turtle myTurtle;
    private volatile boolean isDone = false;

    TurtleAction(Turtle myTurtle) {
        this.myTurtle = myTurtle;
    }

    public synchronized boolean isDone() {
        return isDone;
    }

    @Override
    public void run() {
        isDone = true;
    }

    protected Turtle getMyTurtle() {
        return myTurtle;
    }

}
