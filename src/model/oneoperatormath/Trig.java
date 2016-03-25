// This entire file is part of my masterpiece.
// Rhondu Smithwick
// See https://github.com/duke-compsci308-spring2016/slogo_team12/blob/analysis_rss44/src/model/twooperatormath/TwoOperatorMath.java for explanation
package model.oneoperatormath;

import java.util.function.DoubleUnaryOperator;

/**
 * Created by rhondusmithwick on 3/8/16.
 *
 * @author Rhondu Smithwick
 */
class Trig extends OneOperatorMath {

    Trig(DoubleUnaryOperator func) {
        super(t -> func.applyAsDouble(Math.toRadians(t)));
    }
}
