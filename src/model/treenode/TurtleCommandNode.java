package model.treenode;

import controller.slogoparser.ExpressionTree;
import model.turtle.Turtle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rhondusmithwick on 2/22/16.
 *
 * @author Rhondu Smithwick
 */
public abstract class TurtleCommandNode extends TreeNode {

    private Double value = 0.0;

    private final List<Turtle> myTurtles = new ArrayList<>();

    public abstract double turtleExecute(Turtle turtle);

    private void execute(Turtle turtle) {
        value = turtleExecute(turtle);
    }

    @Override
    public double getValue() {
        System.out.print(myTurtles);
        myTurtles.parallelStream().map(this::createThread).forEach(Thread::start);
        return value;
    }


    private Thread createThread(Turtle turtle) {
        return new Thread(() -> execute(turtle));
    }

    @Override
    public void handleSpecific(ExpressionTree tree) {
        myTurtles.clear();;
        myTurtles.addAll(tree.getTurtleManager().getActiveTurtles());
    }

}
