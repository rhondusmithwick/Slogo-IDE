package Model.Turtle;

import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.util.Duration;

/**
 * Created by rhondusmithwick on 2/27/16.
 *
 * @author Rhondu Smithwick
 */
public class TransitionAction extends TurtleAction {

    private final double distance;
    private final int direction;
    private Point2D pointToMoveTo;

    private final Line penLine;

    private final Node node;

    public TransitionAction(Turtle myTurtle, Node node, Line penLine, double distance, int direction) {
        super(myTurtle);
        this.node = node;
        this.penLine = penLine;
        this.distance = distance;
        this.direction = direction;
    }

    @Override
    public void run() {
        pointToMoveTo = getPointToMoveTo(distance, direction);
        modifyPenLine();
        Transition transition = createTransition(node, pointToMoveTo);
        transition.onFinishedProperty().set(t -> cleanUpMove());
        transition.play();
    }

    public void cleanUpMove() {
        penLine.endXProperty().unbind();
        penLine.endYProperty().unbind();
        getMyTurtle().getTurtleProperties().setLocation(pointToMoveTo);
        super.run();
    }

    private TranslateTransition createTransition(Node node, Point2D pointToMoveTo) {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1));
        transition.setNode(node);
        transition.setToX(pointToMoveTo.getX());
        transition.setToY(pointToMoveTo.getY());
        return transition;
    }

    public void modifyPenLine() {
        Point2D location = getMyTurtle().getTurtleProperties().getLocation();
        penLine.setStartX(location.getX());
        penLine.setStartY(location.getY());
        penLine.endXProperty().bind(node.translateXProperty());
        penLine.endYProperty().bind(node.translateYProperty());
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
