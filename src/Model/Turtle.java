package Model;

import javafx.animation.PathTransition;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

/**
 * Created by rhondusmithwick on 2/22/16.
 *
 * @author Rhondu Smithwick
 */
public class Turtle {
    private final Group root = new Group();
    private final ImageView imageView = new ImageView();
    private final Path path = new Path();
    private final TurtleProperties turtleProperties = new TurtleProperties();

    public Turtle(Image image) {
        addListeners();
        init(image);
        root.getChildren().add(imageView);
    }


    void moveTo(double newX, double newY) {
        path.getElements().clear();
        Point2D location = turtleProperties.getLocation();
        path.getElements().add(new MoveTo(location.getX(), location.getY()));
        path.getElements().add(new LineTo(newX, newY));
        PathTransition pt = new PathTransition(Duration.millis(10000), path);
        pt.setNode(imageView);
        pt.play();
        turtleProperties.setLocation(new Point2D(newX, newY));
    }


    private void addListeners() {
        turtleProperties.visibleProperty().addListener((ov, oldVal, newVal) ->
                imageView.setVisible(newVal));
        turtleProperties.locationProperty().addListener((ov, oldVal, newVal) ->
                imageView.relocate(newVal.getX(), newVal.getY()));
        turtleProperties.penDownProperty().addListener((ov, oldVal, newVal) ->
                path.setVisible(newVal));
        turtleProperties.penColorProperty().addListener((ov, oldVal, newVal) ->
                path.setFill(newVal));
        turtleProperties.imageProperty().addListener((ov, oldVal, newVal) ->
            imageView.setImage(newVal));
    }

    private void init(Image image) {
        turtleProperties.setLocation(new Point2D(0, 0));
        turtleProperties.setVisible(true);
        turtleProperties.setPenDown(true);
        turtleProperties.setPenColor(Color.BLACK);
        turtleProperties.setHeading(new Point2D(0, 0));
    }

    Point2D getDirectionVector() {
        // getHeading returns the Point on the edge of the screen the turtle is currently facing
        Point2D heading = turtleProperties.getHeading();
        Point2D location = turtleProperties.getLocation();
        double dirVectorX = heading.getX() - location.getX();
        double dirVectorY = heading.getY() - location.getY();
        double dirVectorDistance = Math.sqrt(dirVectorX * dirVectorX + dirVectorY * dirVectorY);
        return new Point2D(dirVectorX / dirVectorDistance, dirVectorY / dirVectorDistance);
    }


    public Group getGroup() {
        return root;
    }
}
