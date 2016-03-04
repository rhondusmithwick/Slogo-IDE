package model.turtleboolean;

public class Greater extends TurtleBoolean {

	@Override
	public double conditional() {
		double value1 = getChildren().get(0).getValue();
		double value2 = getChildren().get(1).getValue();
		
		System.out.println(value1 > value2 ? 1 : 0);
		
		return value1 > value2 ? 1 : 0;
	}
}
