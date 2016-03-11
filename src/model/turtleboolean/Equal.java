package model.turtleboolean;

public class Equal extends TurtleBoolean {

    protected Equal() {
        super((a, b) -> (a == b) ? 1 : 0);
    }
}
