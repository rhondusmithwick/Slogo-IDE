// This entire file is part of my masterpiece.
// Rhondu Smithwick
/*
    We had just done the functional programming lab, and turtle math was already done in a different way.
    I let Jonathan (who wrote the old way) that I thought I had a cool way of doing it. I then implemented this using the
    methods we learned in class. It takes in a function in this superclass. It then does the function on its two parameters.
    The math subclasses pass the function up. Originally, we had each one doing the same getting of the parameters and
    outputting the same thing. This keeps all the logic contained in one class, and the subclasses need only pass up a func.
    I also refactored the oneoperatormath package to do this. Jonathan did the same to turtleboolean after viewing this.
    See the original code in this committ here: https://github.com/duke-compsci308-spring2016/slogo_team12/tree/33dd26f03048d33ec86c02592881a94ae27faf80/src/model/turtlemath
    and an example of one of the classes here: https://github.com/duke-compsci308-spring2016/slogo_team12/blob/33dd26f03048d33ec86c02592881a94ae27faf80/src/model/turtlemath/Product.java.
    All of the classes would have that same logic with essentially the only difference the operation.
    See the new product here: https://github.com/duke-compsci308-spring2016/slogo_team12/blob/analysis_rss44/src/model/twooperatormath/Product.java.
    This entire twooperatormath and oneoperatormath packages are my masterpiece.
 */
package model.twooperatormath;

import model.treenode.CommandNode;

import java.util.function.DoubleBinaryOperator;

/**
 * Created by rhondusmithwick on 3/5/16.
 *
 * @author Rhondu Smithwick
 */
public abstract class TwoOperatorMath extends CommandNode {

    private final DoubleBinaryOperator func;

    protected TwoOperatorMath(DoubleBinaryOperator func) {
        this.func = func;
    }

    @Override
    public double execute() {
        double value1 = getChildren().get(0).getValue();
        double value2 = getChildren().get(1).getValue();
        return func.applyAsDouble(value1, value2);
    }

    @Override
    protected int getNumChildrenRequired() {
        return 2;
    }
}
