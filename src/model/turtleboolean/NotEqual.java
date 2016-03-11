package model.turtleboolean;

public class NotEqual extends TurtleBoolean {

    protected NotEqual() {
        super((a, b) -> (a != b) ? 1 : 0);
    }
}
