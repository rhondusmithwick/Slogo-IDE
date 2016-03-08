package model.usercontrol;

import model.treenode.CommandNode;
import model.treenode.TreeNode;

import java.util.LinkedList;
import java.util.List;
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
        TreeNode numTimesNode = getChildren().get(0);
        int numTimes = (int) numTimesNode.getValue();
        IntStream.range(0, numTimes + 1).forEach(i -> runChildren());
        return (value != null) ? value : 0;
    }

    private void runChildren() {
        value = getChildren().stream().map(TreeNode::getValue).reduce((a, b) -> b).orElse(null);
    }
}
