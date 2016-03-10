package model.turn;

import model.turtle.Turtle;

/**
 * Created by rhondusmithwick on 2/27/16.
 *
 * @author Rhondu Smithwick
 */
public class Right extends Turn {
    @Override
    public double turtleExecute(Turtle turtle) {
        return turn(turtle, -1);
    }
}
