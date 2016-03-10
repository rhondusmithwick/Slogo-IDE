package model.turtleboolean;

public class Or extends TurtleBoolean {
	
	protected Or() {
		super((a, b) -> (a != 0 || b != 0) ? 1 : 0);
	}
}
