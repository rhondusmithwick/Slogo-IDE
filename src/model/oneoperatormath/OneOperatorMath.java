package model.oneoperatormath;

import model.treenode.CommandNode;

import java.util.function.DoubleUnaryOperator;

public abstract class OneOperatorMath extends CommandNode {

    protected double calculate(DoubleUnaryOperator func) {
        double value = getChildren().get(0).getValue();
        return func.applyAsDouble(value);
    }

    @Override
    public int getNumChildrenRequired() {
        return 1;
    }
}
