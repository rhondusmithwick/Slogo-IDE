package model.turtlemath;

public class Pi extends TurtleMath {

	@Override
	public double calculate() {
		double result = Math.PI;

		return result;
	}

	@Override
	public int getNumChildrenRequired() {
		return 0;
	}
}
