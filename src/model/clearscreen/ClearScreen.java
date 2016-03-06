package model.clearscreen;

import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import model.treenode.TurtleCommandNode;


public class ClearScreen extends TurtleCommandNode {

    @Override
    protected double execute() {
        Point2D home = getTurtle().getTurtleProperties().getHome();
        Point2D location = getTurtle().getTurtleProperties().getLocation();
        double distance = location.distance(home);
        getTurtle().getTurtleProperties().setLocation(home);
        Platform.runLater(this::clear);
        return distance;
    }

    private void clear() {
        Group turtGroup = getTurtle().getGroup();
        turtGroup.getChildren().clear();
        turtGroup.getChildren().add(getTurtle().getTurtleProperties().getImageView());
    }
}
