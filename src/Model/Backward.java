package Model;

/**
 * Created by rhondusmithwick on 2/23/16.
 *
 * @author Rhondu Smithwick
 */
public class Backward extends Movement {

    public Backward(Turtle myTurtle, double distance) {
        super(myTurtle, distance);
    }

    @Override
    public double execute() {
        return move(-1);
    }

}
