package model.oneoperatormath;

import java.util.function.DoubleUnaryOperator;

public class Tangent extends OneOperatorMath {

    public double execute() {
        DoubleUnaryOperator func = (t -> (Math.tan(Math.toRadians(t))));
        return calculate(func);
    }
}
