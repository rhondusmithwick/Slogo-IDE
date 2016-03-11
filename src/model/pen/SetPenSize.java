package model.pen;

import model.treenode.TurtleCommandNode;
import model.turtle.Turtle;

public class SetPenSize extends TurtleCommandNode {

    private double pixels;

    @Override
    public double turtleExecute(Turtle turtle) {
        setPenSize(turtle);
        return pixels;
    }

    @Override
    protected int getNumChildrenRequired() {
        return 1;
    }

    private void setPenSize(Turtle turtle) {
        pixels = getChildren().get(0).getValue();
        System.out.println(pixels);
        turtle.getTurtleProperties().setPenSize(pixels);
    }
}
