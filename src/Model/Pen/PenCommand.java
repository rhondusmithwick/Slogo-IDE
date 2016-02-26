package Model.Pen;

import Model.TreeNode.TurtleCommandNode;

/**
 * Created by rhondusmithwick on 2/24/16.
 *
 * @author Rhondu Smithwick
 */
abstract class PenCommand extends TurtleCommandNode {

    double changePen(boolean t) {
        getTurtle().getTurtleProperties().setPenDown(t);
        return t ? 1 : 0;
    }
}
