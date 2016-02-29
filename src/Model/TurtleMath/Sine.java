package Model.TurtleMath;

import Model.Action.MathAction;
import Model.Action.TurtleAction;

public class Sine extends TurtleMath {

	@Override
	public double calculate() {
		double value = Math.toRadians(getChildren().get(0).getValue());
		
		double result = Math.sin(value);
		
		TurtleAction action = new MathAction(getTurtle(), result);
		addAction(action);
		
		return result;
	}

	@Override
	protected double execute() {
		return calculate();
	}
	

}
