package model.multipleturtles;

import controller.slogoparser.ExpressionTree;
import model.treenode.CommandNode;
import model.turtle.TurtleManager;

/**
 * Created by rhondusmithwick on 3/10/16.
 *
 * @author Rhondu Smithwick
 */
public class Turtles extends CommandNode {

    private TurtleManager turtleManager;

    @Override
    protected double execute() {
        return turtleManager.numTurtles();
    }

    @Override
    public void handleSpecific(ExpressionTree tree) {
        this.turtleManager = tree.getTurtleManager();
    }
}
