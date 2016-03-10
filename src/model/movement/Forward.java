package model.movement;

import model.turtle.Turtle;

/**
 * Created by rhondusmithwick on 2/22/16.
 *
 * @author Rhondu Smithwick
 */
public class Forward extends Movement {

    @Override
    public double turtleExecute(Turtle turtle) {
        System.out.println("here");
        return move(turtle, 1);
    }

}
