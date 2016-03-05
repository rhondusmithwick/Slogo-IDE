package model.repeat;

import java.util.stream.IntStream;

import model.treenode.CommandNode;
import model.treenode.TreeNode;

/**
 * Created by rhondusmithwick on 3/1/16.
 *
 * @author Rhondu Smithwick
 */
public class Repeat extends CommandNode {
    @Override
    protected double execute() {
        int numTimes = (int) getChildren().get(0).getValue();
        getChildren().remove(0);
//        Double value = null;
        IntStream.range(0, numTimes + 1)
                .forEach(i -> runChildren());
        return 0.0;
    }

    private void runChildren() {
        getChildren().stream().forEach(TreeNode::getValue);
    }
}
