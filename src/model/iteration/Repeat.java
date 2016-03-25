package model.iteration;

import controller.slogoparser.ExpressionTree;

/**
 * Created by rhondusmithwick on 3/1/16.
 *
 * @author Rhondu Smithwick
 */
public class Repeat extends Iteration {
	
	@Override
    public void handleSpecific(ExpressionTree tree) {
        tree.createRoot();
        setStartValue(1);
        setIncrement(1);
        setVariableName(":repcount");
        super.handleSpecific(tree);
    }

}
