package View;

import java.util.ResourceBundle;

import Observables.ObjectObservable;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class MethodDisplay extends DefinedObjectsDisplay {

	private MethodUpdate updater;
	private String displayTitle;
	private ResourceBundle myResources;
	private ObjectObservable<String> intCommand;
	private ObjectObservable<String> parsingLanguage;

	public MethodDisplay(ObjectObservable<String> pLang, ObjectObservable<String> intCommand,
			SimpleStringProperty methods) {
		super(pLang, intCommand, methods);
		// this is not ideal - need to resolve
		this.intCommand = intCommand;
		myResources = getResources();
		parsingLanguage = getParsingLanguage();
		
		displayTitle = myResources.getString("methodDisplayTitle");
		setDisplayTitle(displayTitle);
		initDisplay();
	}

	@Override
	protected void updateDefinedObject(Label label) {
		updater = new MethodUpdate(myResources, intCommand, parsingLanguage);
		updater.updateMethod(label); // different from the method in this class
	}

}
