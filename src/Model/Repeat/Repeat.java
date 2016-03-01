package Model.Repeat;

import Model.TreeNode.CommandNode;
import Model.TreeNode.TreeNode;

import java.util.stream.IntStream;

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
                .forEach((i ->
                        getChildren().stream().forEach(TreeNode::getValue)));
        return 0.0;
    }
}
