package model.iteration;

import controller.slogoparser.ExpressionTree;
import model.treenode.CommandNode;
import model.treenode.TreeNode;
import model.usercontrol.Variable;

import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by rhondusmithwick on 3/11/16.
 *
 * @author Rhondu Smithwick
 */
public abstract class Iteration extends CommandNode {

    private Integer numTimes = null;
    private final Variable variable = new Variable();
    private String variableName;
    private Double value = null;
    private int increment;
    private int startValue;

    @Override
    protected double execute() {
        variable.setValue(startValue);
        if (numTimes == null) {
            numTimes = makeNumTimes();
        }
        IntStream.range(0, numTimes).forEach(i -> doIteration());
        return (value != null) ? value : 0;
    }

    private void doIteration() {
        double newValue = variable.getValue() + increment;
        variable.setValue(newValue);
        value = runChildren();
    }

    protected abstract int makeNumTimes();

    @Override
    public void handleSpecific(ExpressionTree tree) {
        tree.getVariables().put(variableName, variable);
        List<TreeNode> nRoots = tree.getCommandsFromList();
        getChildren().addAll(nRoots);
        tree.getVariables().remove(variableName);
    }

    protected void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    protected int getIncrement() {
        return increment;
    }

    protected void setIncrement(int increment) {
        this.increment = increment;
    }

    protected int getStartValue() {
        return startValue;
    }

    protected void setStartValue(int startValue) {
        this.startValue = startValue;
    }

    @Override
    protected int getNumChildrenRequired() {
        return 2;
    }
}
