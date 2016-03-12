package view.envdisplay;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.ResourceBundle;

import view.Defaults;
import view.Size;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import observables.ObjectObservable;

/**
 * This class serves as the abstract base class for all GUI elements that 
 * display any user-defined objects such as methods or variables
 *
 * @author Stephen
 */

public abstract class DefinedObjectsDisplay {

	private final SimpleStringProperty definedObjects, error;
	private final ResourceBundle myResources;
	private ScrollPane myScrollPane;
	private VBox vBox;
	private String[] definedObjectsArray;
	private final ObjectObservable<String> parsingLanguage;
	private final ObjectObservable<String> internalCommand;

	private String displayTitle;
    private String splitter;

	/**
	 * Serves as the super constructor for any DefinedObjectsDisplay subclass
	 * @param definedObjects simplestringproperty representing objects defined by user
	 * @param parsingLanguage string observable for storing parsing language
	 * @param intCommand string observable to pass commands from this component to command entry
	 * @param error simplestring property used to show error messages
	 */
	protected DefinedObjectsDisplay(SimpleStringProperty definedObjects, ObjectObservable<String> parsingLanguage,
			ObjectObservable<String> internalCommand, SimpleStringProperty error, String splitter, String title) {
		this.internalCommand = internalCommand;
		this.error = error;
		this.parsingLanguage = parsingLanguage;
		this.definedObjects = definedObjects;
		this.myResources = ResourceBundle.getBundle(Defaults.DISPLAY_LOC.getDefault());
		this.splitter = splitter;
		this.displayTitle = myResources.getString(title);
		setScrollPane();
		setListners();
	}

	/**
	 * updates a label which shows a defined variable or method
	 * @param label label whose contents to update
	 */
	protected abstract void updateDefinedObject(Label label);

	private void setScrollPane() {
		myScrollPane = new ScrollPane();
		myScrollPane.setMinViewportWidth(Size.ENV_DISPLAY_WIDTH.getSize());
		myScrollPane.setPrefViewportWidth(Size.ENV_DISPLAY_WIDTH.getSize());
		myScrollPane.setMaxWidth(Size.ENV_DISPLAY_WIDTH.getSize());
		VBox.setVgrow(myScrollPane, Priority.SOMETIMES);
	}

	/**
	 * returns the node containing all components of the display
	 * @return node containing all definedobjectdisplay components
	 */
	public Node getEnvDisplay() {
		return myScrollPane;
	}

	private void setListners() {
		definedObjects.addListener((ov, oldVal, newVal) -> createCurrEnvDisp());
	}

	/**
	 * creates an updater used to update a defined object from user input. 
	 * creates updater using reflection from class name string
	 * @param className string of classname of object to create
	 * @param text text representing user defined object to update
	 * @return updater object used to update the defined object
	 */
	protected EnvActor getUpdater(String className, Label label) {
		try {
			Class<?> classTemp = Class.forName(className);
			Constructor<?> constructor = classTemp.getConstructor(ObjectObservable.class,
					ObjectObservable.class, Label.class);
			Object obj = constructor.newInstance(internalCommand, parsingLanguage, label);
			return (EnvActor) obj;
		} catch (Exception e) {
			e.printStackTrace();
			error.set("");
			error.set(myResources.getString("createUpError"));
		}
		return null;
	}
	
	/**
	 * creates the current display based off of the current state of the definedObjects
	 * simplestring property
	 */
	public void createCurrEnvDisp() {
	       
		vBox = new VBox();
		setTitle();
		String definedObjectsString = definedObjects.get();
		if (definedObjectsString != null) {
			definedObjectsArray = definedObjectsString.split(splitter);
			Arrays.asList(definedObjectsArray).stream().forEach(e->parseString(e));
		}
		myScrollPane.setContent(vBox);
	}
	
	protected abstract void parseString(String text);

	private void setTitle() {
		Label title = new Label(displayTitle);
		title.setAlignment(Pos.TOP_CENTER);
		title.prefWidthProperty().bind(myScrollPane.widthProperty());
		title.setStyle(Defaults.BORDER_COLOR.getDefault());
		vBox.getChildren().add(title);
	}

	protected void setLabel(String definedObject) {
		Label label = new Label(definedObject);
		if (definedObject.length() == 0){
		    return;
		}
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

}