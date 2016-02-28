package Model.Action;

import Model.Turtle.Turtle;

/**
 * Created by rhondusmithwick on 2/27/16.
 *
 * @author Rhondu Smithwick
 */
public class TurnAction extends TurtleAction {

    private final double degrees;

    private final double direction;

    public TurnAction(Turtle myTurtle, double degrees, double direction) {
        super(myTurtle);
        this.degrees = degrees;
        this.direction = direction;
    }

    @Override
    public void run() {
        double currAngle = getMyTurtle().getTurtleProperties().getHeading();
        double newAngle;
        if (direction == 0) {
            newAngle = degrees;
        } else {
            newAngle = currAngle + (direction * degrees);
        }
        getMyTurtle().getTurtleProperties().setHeading(newAngle);
        super.run();
    }
}
