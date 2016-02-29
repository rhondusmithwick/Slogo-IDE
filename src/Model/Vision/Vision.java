package Model.Vision;

import Model.Action.TurtleAction;
import Model.Action.VisionAction;
import Model.TreeNode.TurtleCommandNode;

public abstract class Vision extends TurtleCommandNode {

    protected double show(boolean b) {
        TurtleAction action = new VisionAction(getTurtle(), b);
        addAction(action);
        return b ? 1 : 0;
    }

}
