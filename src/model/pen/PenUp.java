package model.pen;

import model.turtle.Turtle;

/**
 * Created by rhondusmithwick on 2/24/16.
 *
 * @author Rhondu Smithwick
 */
public class PenUp extends PenCommand {

    @Override
    public double turtleExecute(Turtle turtle) {
        return changePen(turtle, false);
    }
}
