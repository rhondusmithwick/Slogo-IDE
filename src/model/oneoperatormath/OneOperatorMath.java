// This entire file is part of my masterpiece.
// Rhondu Smithwick
// See https://github.com/duke-compsci308-spring2016/slogo_team12/blob/analysis_rss44/src/model/twooperatormath/TwoOperatorMath.java for explanation
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
