package model.pen;

import model.treenode.TurtleCommandNode;
import model.turtle.Turtle;

/**
 * Created by rhondusmithwick on 2/24/16.
 *
 * @author Rhondu Smithwick
 */
abstract class PenCommand extends TurtleCommandNode {

    double changePen(Turtle turtle, boolean b) {
        turtle.getTurtleProperties().setPenDown(b);
        return b ? 1 : 0;
    }
}
