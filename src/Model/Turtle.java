package Model;

import javafx.animation.PathTransition;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import java.util.Observable;

/**
 * Created by rhondusmithwick on 2/22/16.
 *
 * @author Rhondu Smithwick
 */
public class Turtle extends Observable {
    private final Group root = new Group();
    private final ImageView imageView = new ImageView();
    private final Path path = new Path();
    private final TurtleProperties turtleProperties = new TurtleProperties();

    public Turtle(Image image) {
        turtleProperties.addListeners(imageView, path);
        turtleProperties.init(image);
        root.getChildren().add(imageView);
        root.getChildren().add(path);
    }


    void moveTo(Point2D pointToMoveTo) {
//        path.getElements().clear();
        Point2D location = turtleProperties.getLocation();
        path.getElements().add(new MoveTo(location.getX(), location.getY()));
        path.getElements().add(new LineTo(pointToMoveTo.getX(), pointToMoveTo.getY()));
        PathTransition pt = new PathTransition(Duration.millis(10000), path, imageView);
        pt.play();
        turtleProperties.setLocation(pointToMoveTo);
    }


    public TurtleProperties getTurtleProperties() {
        return turtleProperties;
    }

    public Group getGroup() {
        return root;
    }
}
