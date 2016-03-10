package model.vision;

import model.turtle.Turtle;

public class ShowTurtle extends Vision {

    @Override
    public double turtleExecute(Turtle turtle) {
        return show(turtle, true);
    }

}
