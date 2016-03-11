package model.iteration;

import controller.slogoparser.ExpressionTree;
import model.treenode.TreeNode;

/**
 * Created by rhondusmithwick on 3/1/16.
 *
 * @author Rhondu Smithwick
 */
public class Repeat extends Iteration {

    private TreeNode numTimesNode;

    @Override
    protected int makeNumTimes() {
        return (int) numTimesNode.getValue();
    }

    public void handleSpecific(ExpressionTree tree) {
        numTimesNode = tree.createRoot();
        setStartValue(1);
        setIncrement(1);
        setVariableName(":repcount");
        super.handleSpecific(tree);
    }

}
