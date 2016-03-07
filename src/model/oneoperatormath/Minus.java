package model.oneoperatormath;

import java.util.function.DoubleUnaryOperator;

public class Minus extends OneOperatorMath {

    public double execute() {
        DoubleUnaryOperator func = (t -> -t);
        return calculate(func);
    }
}
