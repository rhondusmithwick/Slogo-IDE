package model.oneoperatormath;

import model.treenode.CommandNode;

import java.util.function.DoubleUnaryOperator;

public abstract class OneOperatorMath extends CommandNode {

    private final DoubleUnaryOperator func;

    protected OneOperatorMath(DoubleUnaryOperator func) {
        this.func = func;
    }

    @Override
    public double execute() {
        double value = getChildren().get(0).getValue();
        System.out.println(func.applyAsDouble(value));
        return func.applyAsDouble(value);
    }

    @Override
    public int getNumChildrenRequired() {
        return 1;
    }
}
