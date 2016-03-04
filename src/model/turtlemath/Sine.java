package model.turtlemath;

public class Sine extends TurtleMath {

	@Override
	public double calculate() {
		double value = Math.toRadians(getChildren().get(0).getValue());
		
		double result = Math.sin(value);
		
		return result;
	}
}
