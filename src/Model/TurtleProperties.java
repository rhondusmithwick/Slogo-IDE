package Model;


import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Dimension2D;
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

    private static final String DEFAULT_TURTLE_IMAGE = "images/turtle_default.jpg";

    private final SimpleStringProperty image = new SimpleStringProperty(this, "turtleImage");

    private final SimpleBooleanProperty visible = new SimpleBooleanProperty(this, "visible");

    private final SimpleObjectProperty<Point2D> location = new SimpleObjectProperty<>(this, "location");

    private final SimpleObjectProperty<Point2D> home = new SimpleObjectProperty<>(this, "home");

    private final SimpleDoubleProperty heading = new SimpleDoubleProperty(this, "heading");

    private final SimpleBooleanProperty penDown = new SimpleBooleanProperty(this, "penDown");

    private final SimpleStringProperty penColor = new SimpleStringProperty(this, "penColor");


    final void init(Dimension2D turtleDispDimension) {
        setImage(DEFAULT_TURTLE_IMAGE);
        setVisible(true);
        home.set(new Point2D(turtleDispDimension.getWidth() / 2,
                turtleDispDimension.getHeight() / 2));
        setLocation(new Point2D(getHome().getX(), getHome().getY()));
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
                imageView.setImage(createImage(newVal)));
        heading.addListener((ov, oldVal, newVal) ->
                imageView.setRotate(newVal.doubleValue()));
    }

    public final SimpleStringProperty imageProperty() {
        return image;
    }

    public final void setImage(String image) {
        this.image.set(image);
    }

    public void setVisible(boolean visible) {
        this.visible.set(visible);
    }

    public final Point2D getLocation() {
        return location.get();
    }

    public final Point2D getHome() {
        return home.get();
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

    private Image createImage(String filePath) {
        return new Image(getClass().getClassLoader().getResourceAsStream(filePath));
    }

}
