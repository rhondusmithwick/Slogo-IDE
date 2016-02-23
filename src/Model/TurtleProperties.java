package Model;


import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * Created by rhondusmithwick on 2/22/16.
 *
 * @author Rhondu Smithwick
 */
public final class TurtleProperties {

    private final SimpleObjectProperty<Image> image = new SimpleObjectProperty<>();
    private final SimpleBooleanProperty visible = new SimpleBooleanProperty();

    private final SimpleObjectProperty<Point2D> location = new SimpleObjectProperty<>();

    private final SimpleObjectProperty<Point2D> heading = new SimpleObjectProperty<>();

    private final SimpleBooleanProperty penDown = new SimpleBooleanProperty();

    private final SimpleObjectProperty<Color> penColor = new SimpleObjectProperty<>();

    public final SimpleBooleanProperty visibleProperty() {
        return visible;
    }

    public final SimpleObjectProperty<Point2D> locationProperty() {
        return location;
    }


    public Image getImage() {
        return image.get();
    }

    public SimpleObjectProperty<Image> imageProperty() {
        return image;
    }

    public void setImage(Image image) {
        this.image.set(image);
    }

    public boolean getVisible() {
        return visible.get();
    }

    public final Point2D getLocation() {
        return location.get();
    }

    public void setLocation(Point2D location) {
        this.location.set(location);
    }

    public void setVisible(boolean visible) {
        this.visible.set(visible);
    }

    public final Point2D getHeading() {
        return heading.get();
    }

    public void setHeading(Point2D heading) {
        this.heading.set(heading);
    }

    public final SimpleObjectProperty<Point2D> headingProperty() {
        return heading;
    }

    public final boolean getPenDown() {
        return penDown.get();
    }

    public void setPenDown(boolean penDown) {
        this.penDown.set(penDown);
    }

    public final SimpleBooleanProperty penDownProperty() {
        return penDown;
    }

    public final Color getPenColor() {
        return penColor.get();
    }

    public void setPenColor(Color penColor) {
        this.penColor.set(penColor);
    }

    public final SimpleObjectProperty<Color> penColorProperty() {
        return penColor;
    }
}
