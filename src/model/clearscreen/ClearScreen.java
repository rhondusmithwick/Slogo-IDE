package model.clearscreen;

import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import model.treenode.TurtleCommandNode;
import model.turtle.Turtle;


public class ClearScreen extends TurtleCommandNode {

    @Override
    public double turtleExecute(Turtle turtle) {
        Point2D home = turtle.getTurtleProperties().getHome();
        Point2D location = turtle.getTurtleProperties().getLocation();
        double distance = location.distance(home);
        turtle.getTurtleProperties().setLocation(home);
        Platform.runLater(() -> clear(turtle));
        return distance;
    }

    private void clear(Turtle turtle) {
        Group turtGroup = turtle.getGroup();
        turtGroup.getChildren().clear();
        turtGroup.getChildren().add(turtle.getTurtleProperties().getImageView());
    }
}
