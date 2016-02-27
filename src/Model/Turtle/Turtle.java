package Model.Turtle;

import Model.TreeNode.TreeNode;
import javafx.animation.PathTransition;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

/**
 * Created by rhondusmithwick on 2/22/16.
 *
 * @author Rhondu Smithwick
 */
public class Turtle extends TreeNode {
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
        Path path = createPath(pointToMoveTo);
        PathTransition pt = new PathTransition(Duration.seconds(3), path, imageView);
        pt.onFinishedProperty().set(t -> root.getChildren().add(path));
        pt.play();
        turtleProperties.setLocation(pointToMoveTo);
    }

    private Path createPath(Point2D pointToMoveTo) {
        Point2D location = turtleProperties.getLocation();
        Path path = new Path();
        path.setVisible(turtleProperties.getVisible());
        Paint penColor = Paint.valueOf(turtleProperties.getPenColor());
        path.setStroke(penColor);
        path.getElements().add(new MoveTo(location.getX(), location.getY()));
        path.getElements().add(new LineTo(pointToMoveTo.getX(), pointToMoveTo.getY()));
        return path;
    }

    public TurtleProperties getTurtleProperties() {
        return turtleProperties;
    }

    public Group getGroup() {
        return root;
    }

}
