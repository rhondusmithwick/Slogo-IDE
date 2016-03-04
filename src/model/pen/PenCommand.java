package model.pen;

import model.action.PenAction;
import model.action.TurtleAction;
import model.treenode.TurtleCommandNode;

/**
 * Created by rhondusmithwick on 2/24/16.
 *
 * @author Rhondu Smithwick
 */
abstract class PenCommand extends TurtleCommandNode {

    double changePen(boolean b) {
        TurtleAction action = new PenAction(getTurtle(), b);
        addAction(action);
        return b ? 1 : 0;
    }
}
