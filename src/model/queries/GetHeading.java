package model.queries;

import model.treenode.TurtleCommandNode;
import model.turtle.Turtle;

public class GetHeading extends TurtleCommandNode {

    @Override
    public double turtleExecute(Turtle turtle) {
        return turtle.getTurtleProperties().getHeading();
    }

}
