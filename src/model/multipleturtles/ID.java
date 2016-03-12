package model.multipleturtles;

import model.treenode.TurtleCommandNode;
import model.turtle.Turtle;

/**
 * Created by rhondusmithwick on 3/10/16.
 *
 * @author Rhondu Smithwick
 */
public class ID extends TurtleCommandNode {

    @Override
    public double turtleExecute(Turtle turtle) {
        return turtle.getID();
    }
}
