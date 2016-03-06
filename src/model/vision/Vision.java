package model.vision;

import model.treenode.TurtleCommandNode;

public abstract class Vision extends TurtleCommandNode {

    protected double show(boolean b) {
        getTurtle().getTurtleProperties().setVisible(b);
        return b ? 1 : 0;
    }

}
