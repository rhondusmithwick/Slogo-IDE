package model.turtlemath;

public class Tangent extends TurtleMath {

	@Override
	public double calculate() {
		double value = Math.toRadians(getChildren().get(0).getValue());
		
		double result = Math.tan(value);

		return result;
	}
}
