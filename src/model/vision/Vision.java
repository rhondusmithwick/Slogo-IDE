package model.vision;

import model.treenode.TurtleCommandNode;
import model.turtle.Turtle;

abstract class Vision extends TurtleCommandNode {

    double show(Turtle turtle, boolean b) {
        turtle.getTurtleProperties().setVisible(b);
        return b ? 1 : 0;
    }

}
