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

    private final Variable repcount = new Variable();
    private Double value = null;
    private TreeNode numTimesNode;
    private Integer numTimes = null;

    @Override
    protected double execute() {
        repcount.setValue(0);
        if (numTimes == null) {
            getNumTimes();
        }
        IntStream.range(0, numTimes + 1).forEach(i -> runChildren());
        return (value != null) ? value : 0;
    }

    private void runChildren() {
        repcount.setValue(repcount.getValue() + 1);
        System.out.println(repcount.getValue());
        value = getChildren().stream().map(TreeNode::getValue).reduce((a, b) -> b).orElse(null);
    }

    private void getNumTimes() {
        numTimes = (int) numTimesNode.getValue();
    }

    public void handleSpecific(ExpressionTree tree) {
        numTimesNode = tree.createRoot();
        List<TreeNode> nRoots = tree.getCommandsFromList();
        nRoots.stream().forEach(this::addChild);
    }
    
    public Variable getVariable() {
    	return repcount;
    }
}
