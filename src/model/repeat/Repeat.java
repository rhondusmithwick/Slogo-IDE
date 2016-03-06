package model.repeat;

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

    @Override
    protected double execute() {
        int numTimes = (int) getChildren().get(0).getValue();
        getChildren().remove(0);
        IntStream.range(0, numTimes + 1).forEach(i -> runChildren());
        return value;
    }

    private void runChildren() {
        value = getChildren().stream().map(TreeNode::getValue).reduce((a, b) -> b).orElse(null);
    }
}
