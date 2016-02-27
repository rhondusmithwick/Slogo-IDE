package Model.Turtle;

import Model.TreeNode.TreeNode;
import javafx.animation.TranslateTransition;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.util.Duration;

/**
 * Created by rhondusmithwick on 2/22/16.
 *
 * @author Rhondu Smithwick
 */
public class Turtle {
    private final Group root = new Group();
    private final ImageView imageView = new ImageView();
    private final TurtleProperties turtleProperties;


    public Turtle(Dimension2D turtleDispDimension) {
        turtleProperties = new TurtleProperties();
        turtleProperties.addListeners(imageView);
        turtleProperties.init(turtleDispDimension);
        root.getChildren().add(imageView);
    }

    public void moveTo(Point2D pointToMoveTo) {
        Line penLine = createPenLine();
        root.getChildren().add(penLine);
        TranslateTransition transition = createMovement(pointToMoveTo);
        imageView.setVisible(false);
        transition.onFinishedProperty().set(t -> cleanUpMove(penLine, pointToMoveTo));
        getTurtleProperties().setIsMoving(true);
        transition.play();
    }

    private Line createPenLine() {
        Line penLine = new Line();
        Point2D location = turtleProperties.getLocation();
        penLine.setStartX(location.getX());
        penLine.setStartY(location.getY());
        penLine.endXProperty().bind(imageView.translateXProperty());
        penLine.endYProperty().bind(imageView.translateYProperty());
        Paint stroke = Paint.valueOf(turtleProperties.getPenColor());
        penLine.setStroke(stroke);
        penLine.setVisible(turtleProperties.getPenDown());
        return penLine;
    }

    private TranslateTransition createMovement(Point2D pointToMoveTo) {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), imageView);
        transition.setToX(pointToMoveTo.getX());
        transition.setToY(pointToMoveTo.getY());
        transition.setNode(imageView);
        return transition;
    }

    private void cleanUpMove(Line penLine, Point2D pointToMoveTo) {
        penLine.endXProperty().unbind();
        penLine.endYProperty().unbind();
        turtleProperties.setLocation(pointToMoveTo);
        imageView.setVisible(turtleProperties.getVisible());
        getTurtleProperties().setIsMoving(false);
    }

    public TurtleProperties getTurtleProperties() {
        return turtleProperties;
    }

    public Group getGroup() {
        return root;
    }

}
