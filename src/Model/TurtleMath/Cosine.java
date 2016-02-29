package Model.TurtleMath;

public class Cosine extends TurtleMath {

	@Override
	public double calculate() {
		double value = Math.toRadians(getChildren().get(0).getValue());
		
		double result = Math.cos(value);
		
		return result;
	}
}
