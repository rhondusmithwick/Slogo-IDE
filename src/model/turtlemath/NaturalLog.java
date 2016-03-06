package model.turtlemath;

public class NaturalLog extends TurtleMath {

    @Override
    public double calculate() {
        double value = getChildren().get(0).getValue();
        return Math.log(value);
    }
}
