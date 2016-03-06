package model.OneOperatorMath;

import java.util.function.DoubleUnaryOperator;

public class Sine extends OneOperatorMath {

    public double execute() {
        DoubleUnaryOperator func = (t -> (Math.sin(Math.toRadians(t))));
        return calculate(func);
    }
}
