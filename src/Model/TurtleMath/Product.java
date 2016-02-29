package Model.TurtleMath;

import Model.Action.MathAction;
import Model.Action.TurtleAction;

public class Product extends TurtleMath {

	@Override
	public double calculate() {
		double value1 = getChildren().get(0).getValue();
		double value2 = getChildren().get(1).getValue();
		
		double result = value1 * value2;
		
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
