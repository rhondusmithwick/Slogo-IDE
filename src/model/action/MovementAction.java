package model.action;

import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import model.turtle.Turtle;

/**
 * Created by rhondusmithwick on 2/27/16.
 *
 * @author Rhondu Smithwick
 */
public class MovementAction extends TurtleAction {

    private final double distance;
    private final int direction;
    private final Line penLine;
    private Point2D pointToMoveTo;

    public MovementAction(Turtle myTurtle, Line penLine, double distance, int direction) {
        super(myTurtle);
        this.penLine = penLine;
        this.distance = distance;
        this.direction = direction;
    }

    @Override
    public void run() {
        pointToMoveTo = getPointToMoveTo(distance, direction);
        modifyPenLine();
        Transition transition = createTransition(pointToMoveTo);
        transition.onFinishedProperty().set(t -> cleanUpMove());
        transition.play();
    }

    private void cleanUpMove() {
        penLine.endXProperty().unbind();
        penLine.endYProperty().unbind();
        getMyTurtle().getTurtleProperties().setLocation(pointToMoveTo);
        super.run();
    }

    private TranslateTransition createTransition(Point2D pointToMoveTo) {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1));
        transition.setNode(getMyTurtle().getTurtleProperties().getImageView());
        transition.setToX(pointToMoveTo.getX());
        transition.setToY(pointToMoveTo.getY());
        return transition;
    }

    private void modifyPenLine() {
        Point2D location = getMyTurtle().getTurtleProperties().getLocation();
        penLine.setStartX(location.getX());
        penLine.setStartY(location.getY());
        Node imageView = getMyTurtle().getTurtleProperties().getImageView();
        penLine.endXProperty().bind(imageView.translateXProperty());
        penLine.endYProperty().bind(imageView.translateYProperty());
        Paint stroke = Paint.valueOf(getMyTurtle().getTurtleProperties().getPenColor());
        penLine.setStroke(stroke);
        penLine.setVisible(getMyTurtle().getTurtleProperties().getPenDown());
    }

    private Point2D getPointToMoveTo(double distance, int direction) {
        double heading = getMyTurtle().getTurtleProperties().getHeading();
        double angle = Math.toRadians(heading);
        Point2D location = getMyTurtle().getTurtleProperties().getLocation();
        double offsetX = direction * (distance * Math.sin(angle));
        double offsetY = direction * (distance * Math.cos(angle));
        double newX = location.getX() + offsetX;
        double newY = location.getY() + offsetY;
        return new Point2D(newX, newY);
    }
}
