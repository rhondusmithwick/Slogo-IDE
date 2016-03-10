package model.turn;

import model.turtle.Turtle;

/**
 * Created by rhondusmithwick on 2/27/16.
 *
 * @author Rhondu Smithwick
 */
public class SetHeading extends Turn {

    public double turtleExecute(Turtle turtle) {
        double degrees = getChildren().get(0).getValue();
        turn(turtle, 0);
        return degrees;
    }

}
