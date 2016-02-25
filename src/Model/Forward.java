package Model;

/**
 * Created by rhondusmithwick on 2/22/16.
 *
 * @author Rhondu Smithwick
 */
public class Forward extends Movement {

    public Forward(Turtle myTurtle, double distance) {
        super(myTurtle, distance);
    }

    public double execute() {
        return move(1);
    }
}
