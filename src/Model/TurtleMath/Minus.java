package Model.TurtleMath;

import Model.Action.MathAction;
import Model.Action.TurtleAction;

public class Minus extends TurtleMath {

	@Override
	public double calculate() {
		double value = getChildren().get(0).getValue();
		
		double result = value * -1;
		
		TurtleAction action = new MathAction(getTurtle(), result);
		addAction(action);
		
		return result;
	}

	@Override
	protected double execute() {
		return calculate();
	}

}
