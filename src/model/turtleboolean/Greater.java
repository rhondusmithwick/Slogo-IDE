package model.turtleboolean;

public class Greater extends TurtleBoolean {

    protected Greater() {
        super((a, b) -> (a > b) ? 1 : 0);
    }
}
