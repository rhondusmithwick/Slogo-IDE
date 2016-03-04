package model.turtlemath;

public class Difference extends TurtleMath {

	@Override
	public double calculate() {
		double value1 = getChildren().get(0).getValue();
		double value2 = getChildren().get(1).getValue();
		
		double result = value1 - value2;

		return result;
	}
	
	@Override
	public int getNumChildrenRequired() {
		return 2;
	}
}
