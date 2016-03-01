package Model.Movement;

import Model.Action.MovementAction;
import Model.Action.TurtleAction;
import Model.TreeNode.TurtleCommandNode;
import Model.Turtle.Turtle;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.util.Duration;

import java.util.concurrent.TimeUnit;

/**
 * Created by rhondusmithwick on 2/23/16.
 *
 * @author Rhondu Smithwick
 */
abstract class Movement extends TurtleCommandNode {

    private Line penLine = new Line();
    private Point2D pointToMoveTo;
    private volatile boolean isDone = false;

    double move(int direction) {
        Turtle myTurtle = getTurtle();
        double distance = getChildren().get(0).getValue();
        Line penLine = new Line();
        Platform.runLater(() -> myTurtle.getGroup().getChildren().add(penLine));

        pointToMoveTo = getPointToMoveTo(distance, direction);
        pointToMoveTo = getPointToMoveTo(distance, direction);
        modifyPenLine();
        Transition transition = createTransition(pointToMoveTo);
        transition.onFinishedProperty().set(t -> cleanUpMove());
        new Thread(transition::play);
        while (!isDone) {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return distance;
    }

    @Override
    public int getNumChildrenRequired() {
        return 1;
    }


    private void cleanUpMove() {
        penLine.endXProperty().unbind();
        penLine.endYProperty().unbind();
        getTurtle().getTurtleProperties().setLocation(pointToMoveTo);
        isDone = true;
    }

    private TranslateTransition createTransition(Point2D pointToMoveTo) {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1));
        transition.setNode(getTurtle().getTurtleProperties().getImageView());
        transition.setToX(pointToMoveTo.getX());
        transition.setToY(pointToMoveTo.getY());
        return transition;
    }

    private void modifyPenLine() {
        Point2D location = getTurtle().getTurtleProperties().getLocation();
        penLine.setStartX(location.getX());
        penLine.setStartY(location.getY());
        Node imageView = getTurtle().getTurtleProperties().getImageView();
        penLine.endXProperty().bind(imageView.translateXProperty());
        penLine.endYProperty().bind(imageView.translateYProperty());
        Paint stroke = Paint.valueOf(getTurtle().getTurtleProperties().getPenColor());
        penLine.setStroke(stroke);
        penLine.setVisible(getTurtle().getTurtleProperties().getPenDown());
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
}
