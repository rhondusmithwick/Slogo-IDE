package model.iteration;

import controller.slogoparser.ExpressionTree;

public class DoTimes extends Iteration {
	
	@Override
    public void handleSpecific(ExpressionTree tree) {
        createIteration(tree.getParsedText(), 0);
        super.handleSpecific(tree);
    }

}
