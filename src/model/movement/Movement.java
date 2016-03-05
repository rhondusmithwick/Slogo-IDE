package model.movement;

import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.effect.Light;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import model.action.MovementAction;
import model.action.TurtleAction;
import model.treenode.TurtleCommandNode;
import model.turtle.Turtle;

import java.util.concurrent.TimeUnit;

/**
 * Created by rhondusmithwick on 2/23/16.
 *
 * @author Rhondu Smithwick
 */
public abstract class Movement extends TurtleCommandNode {

    private final Line penLine = new Line();

    private volatile boolean isDone = false;

    double move(int direction) {
        double distance = getChildren().get(0).getValue();
        Point2D pointToMoveTo = getPointToMoveTo(distance, direction);
        modifyPenLine();
        Transition transition = createTransition(pointToMoveTo);
        transition.onFinishedProperty().set(t -> cleanUpMove(pointToMoveTo));
        new Thread(transition::play).start();
        keepGoing();
        return distance;
    }

    @Override
    public int getNumChildrenRequired() {
        return 1;
    }


    public void addLine() {
        getTurtle().getGroup().getChildren().add(penLine);
    }

    private void cleanUpMove(Point2D pointToMoveTo) {
        penLine.endXProperty().unbind();
        penLine.endYProperty().unbind();
        getTurtle().getTurtleProperties().setLocation(pointToMoveTo);
        setDone(true);
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


    private synchronized  void setDone(boolean b) {
        isDone = b;
    }

    public void keepGoing() {
        while (!isDone) {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
