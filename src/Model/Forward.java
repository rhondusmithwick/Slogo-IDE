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
        double heading = getTurtle().getTurtleProperties().getHeading();
        double angle = Math.toRadians(heading);
        Point2D location = getTurtle().getTurtleProperties().getLocation();
        double newX = location.getX() + (distance * Math.sin(angle));
        double newY = location.getY() + (distance * Math.cos(angle));
        getTurtle().moveTo(newX, newY);
        return distance;
    }


}
