package model.turtlemath;

public class Pi extends TurtleMath {

    @Override
    public double calculate() {
        return Math.PI;
    }

    @Override
    public int getNumChildrenRequired() {
        return 0;
    }
}
