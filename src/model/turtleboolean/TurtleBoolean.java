package model.turtleboolean;

import model.treenode.CommandNode;

import java.util.function.DoubleBinaryOperator;

public abstract class TurtleBoolean extends CommandNode {

    private final DoubleBinaryOperator func;

    protected TurtleBoolean(DoubleBinaryOperator func) {
        this.func = func;
    }

    @Override
    protected int getNumChildrenRequired() {
        return 2;
    }

    @Override
    protected double execute() {
        double value1 = getChildren().get(0).getValue();
        double value2 = getChildren().get(1).getValue();
        return func.applyAsDouble(value1, value2);
    }
}
