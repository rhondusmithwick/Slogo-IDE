package model.turtlemath;

public class Minus extends TurtleMath {

    @Override
    public double calculate() {
        double value = getChildren().get(0).getValue();
        return value * -1;
    }
}
