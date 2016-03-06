package model.treenode;

import model.turtle.Turtle;

/**
 * Created by rhondusmithwick on 2/22/16.
 *
 * @author Rhondu Smithwick
 */
public abstract class TurtleCommandNode extends CommandNode {

    private Turtle myTurtle;

    protected Turtle getTurtle() {
        return myTurtle;
    }

    public void setTurtle(Turtle myTurtle) {
        this.myTurtle = myTurtle;
    }

    @Override
    public double getValue() {
        return execute();
    }

}
