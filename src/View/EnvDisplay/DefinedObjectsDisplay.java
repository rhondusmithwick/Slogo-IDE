package View.EnvDisplay;

import java.lang.reflect.Constructor;
import java.util.ResourceBundle;

import Observables.ObjectObservable;
import View.Defaults;
import View.Size;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * This class implements EnviromentDisplayInterface and serves as the abstract
 * base class for all GUI elements that display any user-defined objects such as
 * methods or variables
 *
 * @author Stephen
 */

public abstract class DefinedObjectsDisplay {

	private SimpleStringProperty definedObjects, error;
	private ResourceBundle myResources;
	private ScrollPane myScrollPane;
	private VBox vBox;
	private String[] definedObjectsArray;
	private ObjectObservable<String> parsingLanguage;
	private ObjectObservable<String> intCommand;

	private String displayTitle;

	public DefinedObjectsDisplay(SimpleStringProperty definedObjects, ObjectObservable<String> parsingLanguage,
			ObjectObservable<String> intCommand, SimpleStringProperty error) {
		this.intCommand = intCommand;
		this.error = error;
		this.parsingLanguage = parsingLanguage;
		this.definedObjects = definedObjects;
		this.myResources = ResourceBundle.getBundle(Defaults.DISPLAY_LOC.getDefault());
		setScrollPane();
		setListners();
	}

	protected abstract void updateDefinedObject(Label label);

	private void setScrollPane() {
		myScrollPane = new ScrollPane();
		myScrollPane.setMinViewportWidth(Size.ENV_DISPLAY_WIDTH.getSize());
		myScrollPane.setPrefViewportWidth(Size.ENV_DISPLAY_WIDTH.getSize());
		myScrollPane.setMaxWidth(Size.ENV_DISPLAY_WIDTH.getSize());
		VBox.setVgrow(myScrollPane, Priority.SOMETIMES);
	}

	public Node getEnvDisplay() {
		return myScrollPane;
	}

	private void setListners() {
		definedObjects.addListener((ov, oldVal, newVal) -> createCurrEnvDisp());
	}

	protected EnvUpdate getUpdater(String className) {
		try {
			Class<?> classTemp = Class.forName(className);
			Constructor<?> constructor = classTemp.getConstructor(ResourceBundle.class, ObjectObservable.class,
					ObjectObservable.class);
			Object obj = constructor.newInstance(myResources, intCommand, parsingLanguage);
			return (EnvUpdate) obj;
		} catch (Exception e) {
			e.printStackTrace();
			error.set("");
			error.set(myResources.getString("createUpError"));
		}
		return null;
	}

	protected void createCurrEnvDisp() {
		vBox = new VBox();
		setTitle();
		String definedObjectsString = definedObjects.get();
		if (definedObjectsString != null) {
			definedObjectsArray = definedObjectsString.split(",");
			populateVBox();
		}
		myScrollPane.setContent(vBox);
	}

	private void setTitle() {
		Label title = new Label(displayTitle);
		title.setAlignment(Pos.TOP_CENTER);
		title.prefWidthProperty().bind(myScrollPane.widthProperty());
		title.setStyle(Defaults.BORDER_COLOR.getDefault());
		vBox.getChildren().add(title);
	}

	private void populateVBox() {
		for (String defObject : definedObjectsArray) {
			setLabel(defObject);
		}
	}

	private void setLabel(String definedObject) {
		Label label = new Label(definedObject);
		if (definedObject.length() == 0) return;
		label.prefWidthProperty().bind(myScrollPane.widthProperty());
		label.setStyle(Defaults.BORDER_COLOR.getDefault());
		label.setWrapText(true);
		label.setOnMouseClicked(e -> handleUpdate(label));
		vBox.getChildren().add(label);
	}

	private void handleUpdate(Label label) {
		try {
			updateDefinedObject(label);
		} catch (Exception e) {
			error.set("");
			error.set(myResources.getString("envUpdate"));
		}
	}

	public void setDisplayTitle(String displayTitle) {
		this.displayTitle = myResources.getString(displayTitle);
	}
}