package model.turtleboolean;

public class Less extends TurtleBoolean {
	
	protected Less() {
		super((a, b) -> (a < b) ? 1 : 0);
	}
}
