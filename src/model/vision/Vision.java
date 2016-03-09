package model.vision;

import model.treenode.TurtleCommandNode;

abstract class Vision extends TurtleCommandNode {

    double show(boolean b) {
        getTurtle().getTurtleProperties().setVisible(b);
        return b ? 1 : 0;
    }

}
