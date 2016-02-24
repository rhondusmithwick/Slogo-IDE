package Model;


import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;

/**
 * Created by rhondusmithwick on 2/22/16.
 *
 * @author Rhondu Smithwick
 */
public final class TurtleProperties {

    private final SimpleObjectProperty<Image> image = new SimpleObjectProperty<>();

    private final SimpleBooleanProperty visible = new SimpleBooleanProperty();

    private final SimpleObjectProperty<Point2D> location = new SimpleObjectProperty<>();

    private final SimpleDoubleProperty heading = new SimpleDoubleProperty();

    private final SimpleBooleanProperty penDown = new SimpleBooleanProperty();

    private final SimpleStringProperty penColor = new SimpleStringProperty();

    final void init(Image image) {
        setImage(image);
        setVisible(true);
        setLocation(new Point2D(0, 0));
        setHeading(0);
        setPenDown(true);
        setPenColor("black");
    }

    void addListeners(ImageView imageView, Path path) {
        visible.addListener((ov, oldVal, newVal) ->
                imageView.setVisible(newVal));
        location.addListener((ov, oldVal, newVal) ->
                imageView.relocate(newVal.getX(), newVal.getY()));
        penDown.addListener((ov, oldVal, newVal) ->
                path.setVisible(newVal));
        penColor.addListener((ov, oldVal, newVal) ->
                path.setFill(Color.valueOf(newVal)));
        image.addListener((ov, oldVal, newVal) ->
                imageView.setImage(newVal));
        heading.addListener((ov, oldVal, newVal) ->
                imageView.setRotate(newVal.doubleValue()));
    }

    public final void setImage(Image image) {
        this.image.set(image);
    }

    public void setVisible(boolean visible) {
        this.visible.set(visible);
    }

    public final Point2D getLocation() {
        return location.get();
    }

    public void setLocation(Point2D location) {
        this.location.set(location);
    }

    public final double getHeading() {
        return heading.get();
    }

    public void setHeading(double heading) {
        this.heading.set(heading);
    }

    public final void setPenDown(boolean penDown) {
        this.penDown.set(penDown);
    }

    public void setPenColor(String penColor) {
        this.penColor.set(penColor);
    }

    public SimpleStringProperty penColorProperty() {
        return penColor;
    }
}
