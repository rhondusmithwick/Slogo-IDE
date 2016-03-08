package model.oneoperatormath;

public class Sine extends OneOperatorMath {

    public Sine() {
        super((t -> (Math.sin(Math.toRadians(t)))));
    }
}
