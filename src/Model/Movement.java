package Model;

import javafx.geometry.Point2D;

/**
 * Created by rhondusmithwick on 2/23/16.
 *
 * @author Rhondu Smithwick
 */
abstract class Movement extends TurtleCommand {

    private final double distance;

    protected Movement(Turtle myTurtle, double distance) {
        super(myTurtle);
        this.distance = distance;
    }

    protected double move(int direction) {
        Point2D pointToMoveTo = getPointToMoveTo(direction);
        getTurtle().moveTo(pointToMoveTo);
        return distance;
    }

    private Point2D getPointToMoveTo(int direction) {
        double heading = getTurtle().getTurtleProperties().getHeading();
        double angle = Math.toRadians(heading);
        Point2D location = getTurtle().getTurtleProperties().getLocation();
        double offsetX = direction * (distance * Math.sin(angle));
        double offsetY = direction * (distance * Math.cos(angle));
        double newX = location.getX() + offsetX;
        double newY = location.getY() + offsetY;
        return new Point2D(newX, newY);
    }

}
