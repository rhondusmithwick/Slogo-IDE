package View.EnvDisplay;

import java.lang.reflect.Constructor;
import java.util.ResourceBundle;

import Observables.ObjectObservable;
import View.Defaults;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

/**
 * This class implements EnviromentDisplayInterface and serves as the abstract
 * base class for all GUI elements that display any user-defined objects such as
 * methods or variables
 *
 * @author Stephen
 */

public abstract class DefinedObjectsDisplay implements EnvironmentDisplayInterface {

	private static final int SCROLL_HEIGHT = 200;
	private static final int SCROLL_WIDTH = 400;

	private SimpleStringProperty definedObjects, error;
	private String displayLanguage;
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
		this.error= error;
		this.parsingLanguage = parsingLanguage;
		this.definedObjects = definedObjects;
		this.displayLanguage = Defaults.DISPLAY_LANG.getDefault();
		this.myResources = ResourceBundle.getBundle(Defaults.DISPLAY_LOC.getDefault() + displayLanguage);
		myScrollPane = new ScrollPane();
		myScrollPane.setPrefSize(SCROLL_WIDTH, SCROLL_HEIGHT);
	}

	protected abstract void updateDefinedObject(Label label) throws Exception;

	@Override
	public Node getEnvDisplay() {
		return myScrollPane;
	}

	@Override
	public void updateEnvNode() {
		createCurrVDisp();
	}

	protected EnvUpdate getUpdater(String className) throws Exception {
		Class<?> classTemp = Class.forName(className);
		Constructor<?> constructor = classTemp.getConstructor(ResourceBundle.class, ObjectObservable.class, ObjectObservable.class);
		Object obj = constructor.newInstance(myResources, intCommand, parsingLanguage);
		return (EnvUpdate) obj;
	}

	private void createCurrVDisp() {
		vBox = new VBox();
		setTitle();
		String definedObjectsString = definedObjects.get();
		if (definedObjectsString != null) {
			definedObjectsArray = definedObjectsString.split("\n");
			populateVBox();
		}
		myScrollPane.setContent(vBox);
	}

	private void setTitle() {
		Label title = new Label(displayTitle);
		title.setAlignment(Pos.TOP_CENTER);
		title.setPrefWidth(SCROLL_WIDTH);
		title.setStyle(Defaults.BORDER_COLOR.getDefault());
		vBox.getChildren().add(title);
	}

	private void populateVBox() throws Exception {
		for (String definedObject : definedObjectsArray) {
			Label label = new Label(definedObject);
			if (definedObject.length() == 0)
				continue;
			label.setPrefWidth(SCROLL_WIDTH);
			label.setStyle(Defaults.BORDER_COLOR.getDefault());
			label.setWrapText(true);
			try {
				label.setOnMouseClicked(e -> updateDefinedObject(label));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			vBox.getChildren().add(label);
		}
	}

	public ResourceBundle getResources() {
		return myResources;
	}

	public void setDisplayTitle(String displayTitle) {
		this.displayTitle = displayTitle;
	}

}
