package model.oneoperatormath;

public class ArcTangent extends OneOperatorMath {


    public ArcTangent() {
        super((t -> (Math.atan(Math.toRadians(t)))));
    }

}
