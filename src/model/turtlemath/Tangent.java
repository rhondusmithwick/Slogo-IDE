package model.turtlemath;

public class Tangent extends TurtleMath {

    @Override
    public double calculate() {
        double value = Math.toRadians(getChildren().get(0).getValue());
        return Math.tan(value);
    }
}
