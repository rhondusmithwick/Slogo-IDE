package model.treenode;

import controller.slogoparser.ExpressionTree;
import model.turtle.Turtle;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by rhondusmithwick on 2/22/16.
 *
 * @author Rhondu Smithwick
 */
public abstract class TurtleCommandNode extends TreeNode {

    private Double value = 0.0;

    private Future<Double> future;

    private final List<Turtle> myTurtles = new ArrayList<>();
    
    private ExpressionTree tree;

    public abstract double turtleExecute(Turtle turtle);

    @Override
    public double getValue() {
        myTurtles.parallelStream().forEach(this::submit);
        try {
            value = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return value;
    }

    private void submit(Turtle turtle) {
        future = turtle.getExecutorService().submit(() -> turtleExecute(turtle));
    }
    
    public ExpressionTree getTree() {
    	return tree;
    }

    @Override
    public void handleSpecific(ExpressionTree tree) {
    	this.tree = tree;
        myTurtles.clear();;
        myTurtles.addAll(tree.getTurtleManager().getActiveTurtles());
    }

}
