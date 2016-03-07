package model.pen;

import model.treenode.TurtleCommandNode;

/**
 * Created by rhondusmithwick on 2/24/16.
 *
 * @author Rhondu Smithwick
 */
abstract class PenCommand extends TurtleCommandNode {

    double changePen(boolean b) {
        getTurtle().getTurtleProperties().setPenDown(b);
        return b ? 1 : 0;
    }
}
