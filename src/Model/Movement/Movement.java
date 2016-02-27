package Model.Movement;

import Model.TreeNode.TreeNode;
import Model.TreeNode.TurtleCommandNode;
import javafx.geometry.Point2D;

/**
 * Created by rhondusmithwick on 2/23/16.
 *
 * @author Rhondu Smithwick
 */
abstract class Movement extends TurtleCommandNode {

    double move(int direction) {
        TreeNode distanceNode = getChild();
        double distance = distanceNode != null ? distanceNode.getValue() : 0;
        Point2D pointToMoveTo = getPointToMoveTo(distance, direction);
        getTurtle().moveTo(pointToMoveTo);
        return distance;
    }

    private Point2D getPointToMoveTo(double distance, int direction) {
        double heading = getTurtle().getTurtleProperties().getHeading();
        double angle = Math.toRadians(heading);
        Point2D location = getTurtle().getTurtleProperties().getLocation();
        double offsetX = direction * (distance * Math.sin(angle));
        double offsetY = direction * (distance * Math.cos(angle));
        double newX = location.getX() + offsetX;
        double newY = location.getY() + offsetY;
        return new Point2D(newX, newY);
    }

    @Override
    public int getNumChildrenRequired() {
        return 1;
    }
}
