package Model.TurtleMath;

import Model.Action.MathAction;
import Model.Action.TurtleAction;

public class Pi extends TurtleMath {

	@Override
	public double calculate() {
		double result = Math.PI;
		
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
		return 0;
	}
	
	
}
