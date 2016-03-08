package model.oneoperatormath;


public class Cosine extends OneOperatorMath {

    public Cosine() {
        super ((t -> Math.cos(Math.toRadians(t))));
    }
}
