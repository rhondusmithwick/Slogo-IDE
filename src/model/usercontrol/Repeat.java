package model.usercontrol;

import controller.slogoparser.ExpressionTree;
import model.treenode.CommandNode;
import model.treenode.TreeNode;

import java.util.List;
import java.util.stream.IntStream;


/**
 * Created by rhondusmithwick on 3/1/16.
 *
 * @author Rhondu Smithwick
 */
public class Repeat extends CommandNode {

    private Variable repcount;
    private Double value = null;
    private TreeNode numTimesNode;
    private Integer numTimes = null;

    @Override
    protected double execute() {
        repcount.setValue(0);
        if (numTimes == null) {
            getNumTimes();
        }
        IntStream.range(0, numTimes).forEach(i -> doIteration());
        return (value != null) ? value : 0;
    }

    private void doIteration() {
        repcount.setValue(repcount.getValue() + 1);
        value = runChildren();
    }

    private void getNumTimes() {
        numTimes = (int) numTimesNode.getValue();
    }

    public void handleSpecific(ExpressionTree tree) {
        numTimesNode = tree.createRoot();
        List<TreeNode> nRoots = tree.getCommandsFromList();
        System.out.println(nRoots);
        getChildren().addAll(nRoots);
        repcount = (Variable) tree.getVariables().get(":repcount");
    }

    public Variable getVariable() {
        return repcount;
    }
    
    @Override
    protected int getNumChildrenRequired() {
    	return 2;
    }
}
