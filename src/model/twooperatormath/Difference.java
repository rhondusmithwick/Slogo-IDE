package model.twooperatormath;

import java.util.function.DoubleBinaryOperator;

public class Difference extends TwoOperatorMath {

    public double execute() {
        DoubleBinaryOperator func = ((a, b) -> a - b);
        return calculate(func);
    }
}
