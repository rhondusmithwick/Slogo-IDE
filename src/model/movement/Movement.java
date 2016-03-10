package model.movement;

import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import model.treenode.TurtleCommandNode;
import model.turtle.Turtle;

import java.util.concurrent.TimeUnit;

/**
 * Created by rhondusmithwick on 2/23/16.
 *
 * @author Rhondu Smithwick
 */
public abstract class Movement extends TurtleCommandNode {

    double move(Turtle turtle, int direction) {
        SimpleBooleanProperty isDone = new SimpleBooleanProperty(this, "", false);
        Line penLine = setUp(turtle);
        double distance = getChildren().get(0).getValue();
        Point2D pointToMoveTo = getPointToMoveTo(turtle, distance, direction);
        modifyPenLine(turtle, penLine);
        Transition transition = createTransition(turtle, pointToMoveTo);
        transition.onFinishedProperty().set(t -> cleanUpMove(turtle, penLine, pointToMoveTo, isDone));
        new Thread(transition::play).start();
        transition.play();
        keepGoing(isDone);
        return distance;
    }

    private Line setUp(Turtle turtle) {
        Line penLine = new Line();
        penLine.strokeWidthProperty().bind(turtle.getTurtleProperties().penSizeProperty());
        Platform.runLater(() -> turtle.getGroup().getChildren().add(penLine));
        return penLine;
    }

    @Override
    protected int getNumChildrenRequired() {
        return 1;
    }

    private void cleanUpMove(Turtle turtle, Line penLine, Point2D pointToMoveTo, SimpleBooleanProperty isDone) {
        penLine.endXProperty().unbind();
        penLine.endYProperty().unbind();
        turtle.getTurtleProperties().setLocation(pointToMoveTo);
        isDone.set(true);
    }

    private TranslateTransition createTransition(Turtle turtle, Point2D pointToMoveTo) {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(.5));
        transition.setNode(turtle.getTurtleProperties().getImageView());
        transition.setToX(pointToMoveTo.getX());
        transition.setToY(pointToMoveTo.getY());
        return transition;
    }

    private void modifyPenLine(Turtle turtle, Line penLine) {
        Point2D location = turtle.getTurtleProperties().getLocation();
        penLine.setStartX(location.getX());
        penLine.setStartY(location.getY());
        Node imageView = turtle.getTurtleProperties().getImageView();
        penLine.endXProperty().bind(imageView.translateXProperty());
        penLine.endYProperty().bind(imageView.translateYProperty());
        Paint stroke = Paint.valueOf(turtle.getTurtleProperties().getPenColor());
        penLine.setStroke(stroke);
        penLine.setVisible(turtle.getTurtleProperties().getPenDown());
    }

    private Point2D getPointToMoveTo(Turtle turtle, double distance, int direction) {
        double heading = turtle.getTurtleProperties().getHeading();
        double angle = Math.toRadians(heading);
        Point2D location = turtle.getTurtleProperties().getLocation();
        double offsetX = direction * (distance * Math.sin(angle));
        double offsetY = direction * (distance * Math.cos(angle));
        double newX = location.getX() + offsetX;
        double newY = location.getY() + offsetY;
        return new Point2D(newX, newY);
    }

    private void keepGoing(SimpleBooleanProperty isDone) {
        while (!isDone.get()) {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
