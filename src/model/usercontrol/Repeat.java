package model.usercontrol;

import model.treenode.CommandNode;
import model.treenode.TreeNode;

import java.util.stream.IntStream;

/**
 * Created by rhondusmithwick on 3/1/16.
 *
 * @author Rhondu Smithwick
 */
public class Repeat extends CommandNode {

    private Double value = null;
    private Integer numTimes = null;
    private Variable repcount = new Variable();

    @Override
    protected double execute() {
        repcount.setValue(0);
        if (numTimes == null) getNumTimes();
        IntStream.range(0, numTimes + 1).forEach(i -> runChildren());
        return (value != null) ? value : 0;
    }

    private void runChildren() {
        repcount.setValue(repcount.getValue() + 1);
        value = getChildren().stream().map(TreeNode::getValue).reduce((a, b) -> b).orElse(null);
    }


    private void getNumTimes() {
        numTimes = (int) getChildren().get(0).getValue();
        getChildren().remove(0);
    }
}
