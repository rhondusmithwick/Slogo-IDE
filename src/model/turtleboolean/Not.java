package model.turtleboolean;

public class Not extends TurtleBoolean {

	@Override
	public double conditional() {
		double value = getChildren().get(0).getValue();
		
		System.out.println(value == 0 ? 1 : 0);
		
		return value == 0 ? 1 : 0;
	}
	
	@Override
	public int getNumChildrenRequired() {
		return 1;
	}
}
