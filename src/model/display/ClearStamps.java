package model.display;

import javafx.application.Platform;
import model.treenode.TurtleCommandNode;
import model.turtle.Turtle;

public class ClearStamps extends TurtleCommandNode {
    @Override
    public double turtleExecute(Turtle turtle) {
        double value = turtle.getNumStamps() > 0 ? 1 : 0;
        Platform.runLater(turtle::clearStamps);
        return value;
    }
}
