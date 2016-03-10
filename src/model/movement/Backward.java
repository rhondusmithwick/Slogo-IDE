package model.movement;

import model.turtle.Turtle;

/**
 * Created by rhondusmithwick on 2/23/16.
 *
 * @author Rhondu Smithwick
 */
public class Backward extends Movement {

    @Override
    public double turtleExecute(Turtle turtle) {
        return move(turtle, -1);
    }


}
