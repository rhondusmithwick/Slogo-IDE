package view.turtparams;

import java.util.ResourceBundle;

import view.Defaults;
import view.Size;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class TurtleParams {

    private final SimpleObjectProperty<Point2D> location;
    private final SimpleDoubleProperty heading;
    private final SimpleBooleanProperty penDown;;
    private final SimpleStringProperty penColor;


    private ResourceBundle myResources;
    private ScrollPane scroll;

    private Label title;
    private VBox box;

    public TurtleParams(SimpleObjectProperty<Point2D> location,
                        SimpleDoubleProperty heading, SimpleBooleanProperty penDown, SimpleStringProperty penColor) {

        this.location = location;
        this.heading = heading;
        this.penDown = penDown;
        this.penColor = penColor;

        myResources = ResourceBundle.getBundle(Defaults.DISPLAY_LOC.getDefault());
        setScroll();

        box = new VBox();
        box.prefWidthProperty().bind(scroll.widthProperty());
        scroll.setContent(box);
        addTitle();
        addParams();
    }

    private void addParams () {
        box.getChildren().add(createLabel(myResources.getString("penLoc") + location.get()));
        box.getChildren().add(createLabel(myResources.getString("penHead") + heading.get()));
        box.getChildren().add(createLabel(myResources.getString("penStat") + penDown.get()));
        box.getChildren().add(createLabel(myResources.getString("penColor") + penColor.get()));
    }

    private void setScroll() {
        scroll = new ScrollPane();
        scroll.setMinViewportHeight(Size.BOTTOM_HEIGHT.getSize());
        scroll.setPrefViewportHeight(Size.BOTTOM_HEIGHT.getSize());
        scroll.setMaxHeight(Size.BOTTOM_HEIGHT.getSize());
        HBox.setHgrow(scroll, Priority.ALWAYS);
    }


    private void addTitle() {
        title = createLabel(myResources.getString("TurtlePropertiesTitle"));
        box.getChildren().add(title);
    }

    private Label createLabel(String text) {
        Label label = new Label(text);
        label.setAlignment(Pos.TOP_CENTER);
        label.setStyle(Defaults.BORDER_COLOR.getDefault());
        label.prefWidthProperty().bind(scroll.widthProperty());
        label.setWrapText(true);
        return label;
    }

    public Node getTurtleParams() {
        return scroll;
    }

}
