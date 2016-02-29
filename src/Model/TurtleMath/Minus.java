package Model.TurtleMath;

public class Minus extends TurtleMath {

	@Override
	public double calculate() {
		double value = getChildren().get(0).getValue();
		
		double result = value * -1;

		return result;
	}
}
