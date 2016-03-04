package model.turn;

/**
 * Created by rhondusmithwick on 2/27/16.
 *
 * @author Rhondu Smithwick
 */
public class SetHeading extends Turn {

    public double execute() {
        double degrees = getChildren().get(0).getValue();
        turn(0);
        return degrees;
    }

}