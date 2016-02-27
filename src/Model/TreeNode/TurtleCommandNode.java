package Model.TreeNode;

import Model.Turtle.Turtle;
import java.util.concurrent.TimeUnit;

/**
 * Created by rhondusmithwick on 2/22/16.
 *
 * @author Rhondu Smithwick
 */
public abstract class TurtleCommandNode extends CommandNode {

    private Turtle myTurtle;

    public void setTurtle(Turtle myTurtle) {
        this.myTurtle = myTurtle;
    }

    protected Turtle getTurtle() {
        return myTurtle;

    }

    @Override
    public double getValue() {
        return execute();
    }

    @Override
    public long getDelay(TimeUnit unit) {
        if (getTurtle().getTurtleProperties().getIsMoving()) {
            return 5000L;
        }
        return super.getDelay(unit);
    }
}
