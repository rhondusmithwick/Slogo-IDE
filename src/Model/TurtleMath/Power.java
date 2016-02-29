package Model.TurtleMath;

public class Power extends TurtleMath {

	@Override
	public double calculate() {
		double base = getChildren().get(0).getValue();
		double exp = getChildren().get(1).getValue();
		
		double result = Math.pow(base, exp);
		
		return result;
	}

	@Override
	public int getNumChildrenRequired() {
		return 2;
	}
}
