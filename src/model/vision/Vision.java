package model.vision;

import model.action.TurtleAction;
import model.action.VisionAction;
import model.treenode.TurtleCommandNode;

public abstract class Vision extends TurtleCommandNode {

    protected double show(boolean b) {
        TurtleAction action = new VisionAction(getTurtle(), b);
        addAction(action);
        return b ? 1 : 0;
    }

}
