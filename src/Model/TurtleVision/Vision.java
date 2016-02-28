package model.turtlevision;

import model.action.TurtleAction;
import model.action.VisionAction;
import model.treenode.TurtleCommandNode;

abstract class Vision extends TurtleCommandNode {

    protected double show(boolean b) {
        TurtleAction action = new VisionAction(getTurtle(), b);
        addAction(action);
        return b ? 1 : 0;
    }

}
