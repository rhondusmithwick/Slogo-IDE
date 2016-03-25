package model.iteration;

import controller.slogoparser.ExpressionTree;

public class For extends Iteration {

    @Override
    public void handleSpecific(ExpressionTree tree) {
        createIteration(tree.getParsedText(), 1);
        super.handleSpecific(tree);
    }
}
