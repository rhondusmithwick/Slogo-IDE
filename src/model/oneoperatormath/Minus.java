package model.oneoperatormath;

import java.util.function.DoubleUnaryOperator;

public class Minus extends OneOperatorMath {

    public Minus() {
        super((t -> -t));
    }
}
