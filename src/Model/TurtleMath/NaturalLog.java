package Model.TurtleMath;

public class NaturalLog extends TurtleMath {

	@Override
	public double calculate() {
		double value = getChildren().get(0).getValue();
		
		double result = Math.log(value);

		return result;
	}
}
