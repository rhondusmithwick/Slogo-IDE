package view.cons;

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

public class Console {

	private final SimpleObjectProperty<Point2D> location;
	private final SimpleDoubleProperty heading;
	private final SimpleBooleanProperty penDown;;
	private final SimpleStringProperty penColor;
	
	private final String LOCATION_LABEL = "Location: ";
	private final String HEADING_LABEL = "Heading: ";
	private final String PEN_DOWN_LABEL = "Pen Status: ";
	private final String PEN_COLOR = "Pen Color: ";

	private ResourceBundle myResources;
	private ScrollPane scroll;
	private Label contents;
	private Label title;
	private VBox box;
	private SimpleStringProperty consoleInput;

	public Console(SimpleStringProperty consoleInput, SimpleObjectProperty<Point2D> location,
			SimpleDoubleProperty heading, SimpleBooleanProperty penDown, SimpleStringProperty penColor) {
		this.consoleInput = consoleInput;
		this.location = location;
		this.heading = heading;
		this.penDown = penDown;
		this.penColor = penColor;
//		addListner();

		myResources = ResourceBundle.getBundle(Defaults.DISPLAY_LOC.getDefault());
		setScroll();

		box = new VBox();
		box.prefWidthProperty().bind(scroll.widthProperty());
		scroll.setContent(box);
		addTitle();
		box.getChildren().add(createLabel(PEN_DOWN_LABEL + penDown.get()));
	}

	private void setScroll() {
		scroll = new ScrollPane();
		scroll.setMinViewportHeight(Size.BOTTOM_HEIGHT.getSize());
		scroll.setPrefViewportHeight(Size.BOTTOM_HEIGHT.getSize());
		scroll.setMaxHeight(Size.BOTTOM_HEIGHT.getSize());
		HBox.setHgrow(scroll, Priority.ALWAYS);
	}

//	private void addListner() {
//		consoleInput.addListener((ov, oldVal, newVal) -> addContents(newVal));
//	}

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

//	private void addContents(String s) {
//		if (contents == null) {
//			createNew(s);
//		} else {
//			addToCurrent(s);
//		}
//	}
//
//	private void addToCurrent(String s) {
//		String curr = contents.getText();
//		curr = curr + s;
//		contents.setText(curr);
//
//	}

//	private void createNew(String s) {
//		String end;
//		if (s.endsWith("\n")) {
//			end = "";
//		} else {
//			end = "\n";
//		}
//		contents = createLabel(s + end);
//		contents.setOnMouseClicked(e -> clearConsole());
//		box.getChildren().add(contents);
//	}

//	private void clearConsole() {
//		box.getChildren().remove(contents);
//	}

	public Node getConsole() {
		return scroll;
	}

}
