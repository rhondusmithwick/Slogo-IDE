package Model;

/**
 * Created by rhondusmithwick on 2/24/16.
 *
 * @author Rhondu Smithwick
 */
public class PenDown extends PenCommand {

    public PenDown(Turtle myTurtle) {
        super(myTurtle);
    }

    public double execute() {
        changePen(true);
        return 1;
    }
}
