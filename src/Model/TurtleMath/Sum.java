package Model.TurtleMath;

import Model.Action.MathAction;
import Model.Action.TurtleAction;

public class Sum extends TurtleMath {

	@Override
	protected double execute() {
		return calculate();
	}
	
	public double calculate() {
		double value1 = getChildren().get(0).getValue();
		double value2 = getChildren().get(1).getValue();
		
		double result = value1 + value2;
		
		TurtleAction action = new MathAction(getTurtle(), result);
		addAction(action);
				
		return result;
	}
		
	@Override
	public int getNumChildrenRequired() {
		return 2;
	}
}
