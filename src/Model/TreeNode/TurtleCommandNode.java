package Model.TreeNode;

import Model.Turtle.Turtle;
import Model.Turtle.TurtleAction;

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

    protected void addAction(TurtleAction action) {
        myTurtle.addAction(action);
    }
}
