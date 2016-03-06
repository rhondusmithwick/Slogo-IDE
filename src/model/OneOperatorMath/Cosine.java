package model.OneOperatorMath;

import java.util.function.DoubleUnaryOperator;

public class Cosine extends OneOperatorMath {
    @Override
    protected double execute() {
        DoubleUnaryOperator func = (t -> (Math.cos(Math.toRadians(t))));
        return calculate(func);
    }
}
