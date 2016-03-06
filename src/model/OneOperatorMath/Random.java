package model.oneoperatormath;

import java.util.function.DoubleUnaryOperator;

public class Random extends OneOperatorMath {
    @Override
    public double execute() {
        DoubleUnaryOperator func = (t -> Math.random() * t);
        return calculate(func);
    }
}
