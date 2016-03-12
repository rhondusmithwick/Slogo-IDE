package model.treenode;

import controller.slogoparser.ExpressionTree;
import model.turtle.Turtle;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by rhondusmithwick on 2/22/16.
 *
 * @author Rhondu Smithwick
 */
public abstract class TurtleCommandNode extends TreeNode {

    private final List<Turtle> myTurtles = new LinkedList<>();
    private final Map<Turtle, Future<Double>> turtleFutureMap = new HashMap<>();

    protected abstract double turtleExecute(Turtle turtle);

    @Override
    public double getValue() {
        Turtle lastTurtle = myTurtles.get(myTurtles.size() - 1);
        myTurtles.parallelStream().forEach(this::submit);
        Double value;
        try {
            value = turtleFutureMap.get(lastTurtle).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            value = 0.0;
        }
        return value;
    }

    private void submit(Turtle turtle) {
        Future<Double> future = turtle.getExecutorService().submit(() -> turtleExecute(turtle));
        turtleFutureMap.put(turtle, future);
    }

    @Override
    public void handleSpecific(ExpressionTree tree) {
        myTurtles.clear();
        turtleFutureMap.clear();
        myTurtles.addAll(tree.getTurtleManager().getActiveTurtles());
    }

}
