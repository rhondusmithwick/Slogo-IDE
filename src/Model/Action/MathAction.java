package Model.Action;

import Model.Turtle.Turtle;

public class MathAction extends TurtleAction {
	
	private final double value;

	public MathAction(Turtle myTurtle, double value) {
		super(myTurtle);
		this.value = value;
	}
	
	@Override
	public void run() {
		System.out.println(value);
		super.run();
	}

}
