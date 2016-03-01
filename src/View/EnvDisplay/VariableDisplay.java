package View.EnvDisplay;

import java.util.ResourceBundle;

import Observables.ObjectObservable;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;


public class VariableDisplay extends DefinedObjectsDisplay {

	private EnvUpdate updater;
	private String displayTitle;
	private ResourceBundle myResources;
	private ObjectObservable<String> intCommand;
	private ObjectObservable<String> parsingLanguage;

	public VariableDisplay(ObjectObservable<String> pLang, ObjectObservable<String> intCommand,
			SimpleStringProperty methods) {
		super(pLang, intCommand, methods);
		// this is not ideal - need to resolve
		this.intCommand = intCommand;
		myResources = getResources();
		parsingLanguage = getParsingLanguage();
		
		displayTitle = myResources.getString("varTitle");
		setDisplayTitle(displayTitle);
		updateEnvNode();
	}

	@Override
	protected void updateDefinedObject(Label label) {
		updater = new VariableUpdate(myResources, intCommand, parsingLanguage);
		updater.updateEnv(label);
	}
}