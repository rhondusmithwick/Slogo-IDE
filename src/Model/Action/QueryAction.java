package Model.Action;

import Model.Turtle.Turtle;

public class QueryAction extends TurtleAction {
	
	private final double value;
	private final boolean b;
	
	private final boolean isValue;
	
	public QueryAction(Turtle myTurtle, double value) {
		super(myTurtle);
		this.value = value;
		this.b = false;
		this.isValue = true;
	}
	
	public QueryAction(Turtle myTurtle, boolean b) {
		super(myTurtle);
		this.value = 0;
		this.b = b;
		this.isValue = false;
	}
	
	@Override
	public void run() {
		//Just prints to console until we figure out how to connect to frontend.		
		if (isValue) {
			System.out.println(value);
		} else {
			System.out.println(b);
		}
		super.run();
	}

}
