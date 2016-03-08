package model.oneoperatormath;

import java.util.function.DoubleUnaryOperator;

public class NaturalLog extends OneOperatorMath {

    @Override
    protected double execute() {
        DoubleUnaryOperator func = (t -> (Math.log(t)));
        return calculate(func);
    }
}
