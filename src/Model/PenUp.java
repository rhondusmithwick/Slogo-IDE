package Model;

/**
 * Created by rhondusmithwick on 2/24/16.
 *
 * @author Rhondu Smithwick
 */
public class PenUp extends PenCommand {

    public PenUp(Turtle myTurtle) {
        super(myTurtle);
    }

    public double execute() {
        changePen(false);
        return 0;
    }
}
