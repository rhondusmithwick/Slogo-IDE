package model.vision;

import model.turtle.Turtle;

public class HideTurtle extends Vision {

    @Override
    public double turtleExecute(Turtle turtle) {
        return show(turtle, false);
    }
}
