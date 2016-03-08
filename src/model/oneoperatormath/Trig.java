package model.oneoperatormath;

import java.util.function.DoubleUnaryOperator;

/**
 * Created by rhondusmithwick on 3/8/16.
 *
 * @author Rhondu Smithwick
 */
public class Trig extends OneOperatorMath {

    protected Trig(DoubleUnaryOperator func) {
        super(t -> func.applyAsDouble(Math.toRadians(t)));
    }
}
