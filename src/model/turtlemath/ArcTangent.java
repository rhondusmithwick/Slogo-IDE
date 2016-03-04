package model.turtlemath;

public class ArcTangent extends TurtleMath {

	@Override
	public double calculate() {
		double value = Math.toRadians(getChildren().get(0).getValue());
		
		double result = Math.atan(value);
		
		return result;
	}
}
