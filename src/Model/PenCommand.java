package Model;

/**
 * Created by rhondusmithwick on 2/24/16.
 *
 * @author Rhondu Smithwick
 */
public abstract class PenCommand extends TurtleCommand {

    public PenCommand(Turtle myTurtle) {
        super(myTurtle);
    }

    protected void changePen(boolean t) {
        getTurtle().getTurtleProperties().setPenDown(t);
    }
}
