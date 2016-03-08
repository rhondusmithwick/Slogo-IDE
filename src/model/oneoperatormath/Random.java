package model.oneoperatormath;

public class Random extends OneOperatorMath {

    public Random() {
        super((t -> Math.random() * t));
    }
}
