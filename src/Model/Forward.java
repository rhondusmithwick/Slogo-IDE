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
        double newX = getTurtle().getX() + (distance * unitDirVector.getX());
        double newY = getTurtle().getY() + (distance * unitDirVector.getY());
        // have turtle move to (newX, newY) drawing a line
        // in the process if its pen is down
        getTurtle().moveTo(newX, newY);
        return distance;
    }


}
