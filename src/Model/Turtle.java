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

/**
 * Created by rhondusmithwick on 2/22/16.
 *
 * @author Rhondu Smithwick
 */
public class Turtle {
    private final Group root = new Group();
    private ImageView imageView;

    public Turtle(Image image) {
        imageView = new ImageView(image);
        imageView.setX(0);
        imageView.setY(0);
        root.getChildren().add(imageView);
    }


    void moveTo(double newX, double newY) {
        Path path = new Path();
        path.getElements().add(new MoveTo(imageView.getX(), imageView.getY()));
        path.getElements().add(new LineTo(newX, newY));
        PathTransition pt = new PathTransition(Duration.millis(10000), path);
        pt.setNode(imageView);
    }


    Point2D getDirectionVector() {
        // getHeading returns the Point on the edge of the screen the turtle is currently facing
        Point2D heading = getHeading();
        double dirVectorX = heading.getX() - getX();
        double dirVectorY = heading.getY() - getY();
        double dirVectorDistance = Math.sqrt(dirVectorX * dirVectorX + dirVectorY * dirVectorY);
        return new Point2D(dirVectorX / dirVectorDistance, dirVectorY / dirVectorDistance);
    }

    public void setImage(Image image) {
        imageView.setImage(image);
    }

    public Group getGroup() {
        return root;
    }

    double getX() {
        return imageView.getX();
    }

    double getY() {
        return imageView.getY();
    }

    Point2D getHeading() {
        return new Point2D(0, 0);
    }

}
