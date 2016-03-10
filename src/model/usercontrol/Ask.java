package model.usercontrol;

import controller.slogoparser.ExpressionTree;
import model.treenode.TreeNode;
import model.turtle.TurtleManager;
import model.treenode.CommandNode;
import model.turtle.Turtle;

import java.util.Collection;
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
        List<Turtle> oldActives = manager.getActiveTurtles();
        Collection<Integer> newActives = manager.doTell(tree.getParsedText());
        manager.populateActiveTurtles(newActives);
        List<TreeNode> myRoots = tree.getCommandsFromList();
        getChildren().addAll(myRoots);
        manager.replaceActiveTurtles(oldActives);
    }
}
