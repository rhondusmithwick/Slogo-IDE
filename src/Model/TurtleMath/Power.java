package Model.TurtleMath;

import Model.Action.MathAction;
import Model.Action.TurtleAction;

public class Power extends TurtleMath {

	@Override
	public double calculate() {
		double base = getChildren().get(0).getValue();
		double exp = getChildren().get(1).getValue();
		
		double result = Math.pow(base, exp);
		
		TurtleAction action = new MathAction(getTurtle(), result);
		addAction(action);
		
		return result;
	}

	@Override
	protected double execute() {
		return calculate();
	}
	
	@Override
	public int getNumChildrenRequired() {
		return 2;
	}

}
