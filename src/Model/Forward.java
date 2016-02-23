package Model;

import javafx.geometry.Point2D;

/**
 * Created by rhondusmithwick on 2/22/16.
 *
 * @author Rhondu Smithwick
 */
public class Forward extends TurtleCommand {
    private final double distance;

    public Forward(Turtle myTurtle, double distance) {
        super(myTurtle);
        this.distance = distance;
    }

    public double execute() {
        Point2D unitDirVector = getTurtle().getDirectionVector();
        Point2D location = getTurtle().getTurtleProperties().getLocation();
        double newX = location.getX() + (distance * unitDirVector.getX());
        double newY = location.getY() + (distance * unitDirVector.getY());
        getTurtle().moveTo(newX, newY);
        return distance;
    }


}
