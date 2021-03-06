package model.multipleturtles;

import controller.slogoparser.ExpressionTree;
import model.treenode.CommandNode;
import model.treenode.TreeNode;
import model.turtle.Turtle;
import model.turtle.TurtleManager;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by rhondusmithwick on 3/10/16.
 *
 * @author Rhondu Smithwick
 */
public class Ask extends CommandNode {

    @Override
    public double execute() {
        return runChildren();
    }

    @Override
    public void handleSpecific(ExpressionTree tree) {
        TurtleManager manager = tree.getTurtleManager();
        Collection<Turtle> oldActives = new LinkedList<>(manager.getActiveTurtles());
        Collection<Integer> newActives = manager.doTell(tree.getParsedText());
        manager.populateActiveTurtles(newActives);
        List<TreeNode> myRoots = tree.getCommandsFromList();
        getChildren().addAll(myRoots);
        manager.replaceActiveTurtles(oldActives);
    }
}
