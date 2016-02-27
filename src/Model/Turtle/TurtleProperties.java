package Model.Turtle;


import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by rhondusmithwick on 2/22/16.
 *
 * @author Rhondu Smithwick
 */
public final class TurtleProperties {

    private static final String DEFAULT_TURTLE_IMAGE = "images/blackarrow.png";

    private final SimpleStringProperty image = new SimpleStringProperty(this, "turtleImage");

    private final SimpleObjectProperty<Dimension2D> imageDimensions = new SimpleObjectProperty<>(this, "imageDimensions");

    private final SimpleBooleanProperty visible = new SimpleBooleanProperty(this, "visible");

    private final SimpleObjectProperty<Point2D> location = new SimpleObjectProperty<>(this, "location");

    private final SimpleObjectProperty<Point2D> position = new SimpleObjectProperty<>(this, "position");

    private final SimpleObjectProperty<Point2D> home = new SimpleObjectProperty<>(this, "home");
    private final SimpleDoubleProperty heading = new SimpleDoubleProperty(this, "heading");
    private final SimpleBooleanProperty penDown = new SimpleBooleanProperty(this, "penDown");
    private final SimpleStringProperty penColor = new SimpleStringProperty(this, "penColor");

    public boolean getVisible() {
        return visible.get();
    }

    public void setVisible(boolean visible) {
        this.visible.set(visible);
    }

    public String getPenColor() {
        return penColor.get();
    }

    public void setPenColor(String penColor) {
        this.penColor.set(penColor);
    }

    final void init(Dimension2D turtleDispDimension) {
        setImage(DEFAULT_TURTLE_IMAGE);
        setVisible(true);
        home.set(new Point2D(turtleDispDimension.getWidth() / 2 - imageDimensions.get().getWidth() / 2,
                turtleDispDimension.getHeight() / 2 - imageDimensions.get().getHeight() / 2));
        setPosition(getHome());
        setLocation(getHome());
        setHeading(180);
        setPenDown(true);
        setPenColor("black");
    }

    void addListeners(ImageView imageView) {
        visible.addListener((ov, oldVal, newVal) ->
                imageView.setVisible(newVal));
        position.addListener((ov, oldVal, newVal) -> {
            imageView.setX(newVal.getX());
            imageView.setY(newVal.getY());
            location.set(newVal);
        });
        image.addListener((ov, oldVal, newVal) -> {
            Image theImage = createImage(newVal);
            imageView.setImage(theImage);
            imageDimensions.set(new Dimension2D(theImage.getWidth(), theImage.getHeight()));
        });
//        heading.addListener((ov, oldVal, newVal) ->
//                imageView.setRotate(newVal.doubleValue()));
    }

    public final SimpleStringProperty imageProperty() {
        return image;
    }

    public final SimpleStringProperty penColorProperty() {
        return penColor;
    }

    public void setPosition(Point2D position) {
        this.position.set(position);
    }

    public final void setImage(String image) {
        this.image.set(image);
    }

    public final Point2D getLocation() {
        return location.get();
    }

    public void setLocation(Point2D location) {
        this.location.set(location);
    }

    public final Point2D getHome() {
        return home.get();
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

    private Image createImage(String filePath) {
        return new Image(filePath);
    }

}
