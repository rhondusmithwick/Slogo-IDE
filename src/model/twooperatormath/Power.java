package model.twooperatormath;

import java.util.function.DoubleBinaryOperator;

public class Power extends TwoOperatorMath {

    public double execute() {
        DoubleBinaryOperator func = (Math::pow);
        return calculate(func);
    }
}
