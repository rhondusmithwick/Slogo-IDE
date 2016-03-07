package model.turtleboolean;

public class Or extends TurtleBoolean {

    @Override
    public double conditional() {
        double value1 = getChildren().get(0).getValue();
        double value2 = getChildren().get(1).getValue();

        double result;
        if (value1 != 0 || value2 != 0) {
            result = 1;
        } else {
            result = 0;
        }

        System.out.println(result);

        return result;
    }
}
