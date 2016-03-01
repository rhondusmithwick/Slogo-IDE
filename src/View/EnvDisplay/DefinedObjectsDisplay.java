package View.EnvDisplay;

import java.util.ResourceBundle;
import Observables.ObjectObservable;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

/**
 * This class implements EnviromentDisplayInterface and serves as the
 * abstract base class for all GUI elements that display any user-defined
 * objects such as methods or variables
 *
 * @author Stephen
 */

public abstract class DefinedObjectsDisplay implements EnvironmentDisplayInterface {

	private static final int SCROLL_HEIGHT = 200;
	private static final int SCROLL_WIDTH = 400;
	private static final String DEFAULT_LOCATION = "resources/guiStrings/";
	private static final String DISP = "disp";
	private static final String DEFAULT_LANGUAGE = "English";
	private static final String CSS_BLACK_BORDER = "-fx-border-color: black;";

	private SimpleStringProperty definedObjects;
	private String displayLanguage;
	private ResourceBundle myResources;
	private ScrollPane myScrollPane;
	private VBox vBox;
	private String[] definedObjectsArray;
	private ObjectObservable<String> parsingLanguage, intCommand;
	
	private String displayTitle;

	public DefinedObjectsDisplay(ObjectObservable<String> pLang, ObjectObservable<String> intCommand,
			SimpleStringProperty definedObjects) {
		this.intCommand = intCommand;
		this.parsingLanguage = pLang;
		this.definedObjects = definedObjects;
		this.displayLanguage = DEFAULT_LANGUAGE;
		myResources = ResourceBundle.getBundle(DEFAULT_LOCATION + displayLanguage + DISP);
		myScrollPane = new ScrollPane();
		myScrollPane.setPrefSize(SCROLL_WIDTH, SCROLL_HEIGHT);
	}

	protected abstract void updateDefinedObject(Label label);
	
	@Override
	public Node getEnvDisplay() {
		return myScrollPane;
	}

	@Override
	public void updateEnvNode() {
		createCurrVDisp();
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
		title.setStyle(CSS_BLACK_BORDER);
		vBox.getChildren().add(title);
	}

	private void populateVBox() {
		for (String definedObject : definedObjectsArray) {
			Label label = new Label(definedObject);
			if (definedObject.length() == 0)
				continue;
			label.setPrefWidth(SCROLL_WIDTH);
			label.setStyle(CSS_BLACK_BORDER);
			label.setWrapText(true);
			label.setOnMouseClicked(e -> updateDefinedObject(label));
			vBox.getChildren().add(label);
		}
	}
	
	public ObjectObservable<String> getParsingLanguage() {
		return parsingLanguage;
	}
	
	public ResourceBundle getResources() {
		return myResources;
	}
	
	public void setDisplayTitle(String displayTitle) {
		this.displayTitle = displayTitle;
	}

}
