package model.turtleboolean;

public class And extends TurtleBoolean {

    protected And() {
        super((a, b) -> (a != 0 && b != 0) ? 1 : 0);
    }
}
