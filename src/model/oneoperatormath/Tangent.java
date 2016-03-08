package model.oneoperatormath;


public class Tangent extends OneOperatorMath {

    public Tangent() {
        super((t -> (Math.tan(Math.toRadians(t)))));
    }

}
