package Model;

/**
 * Created by rhondusmithwick on 2/22/16.
 *
 * @author Rhondu Smithwick
 */
public abstract class TurtleCommand implements Command {
    private final Turtle myTurtle;

    public TurtleCommand(Turtle myTurtle) {
        this.myTurtle = myTurtle;
    }

    protected Turtle getTurtle() {
        return myTurtle;
    }

}
