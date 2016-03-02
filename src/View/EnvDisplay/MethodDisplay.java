package View.EnvDisplay;

import java.util.ResourceBundle;

import Observables.ObjectObservable;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;

public class MethodDisplay extends DefinedObjectsDisplay {

	private EnvUpdate updater;
	private String displayTitle;
	private ResourceBundle myResources;
	private ObjectObservable<String> intCommand;
	private ObjectObservable<String> parsingLanguage;

	public MethodDisplay(ObjectObservable<String> parsingLanguage, ObjectObservable<String> intCommand,
			SimpleStringProperty methods) {
		super(methods);

		// this is not ideal - need to resolve
		this.intCommand = intCommand;
		this.parsingLanguage = parsingLanguage;
		myResources = getResources();

		displayTitle = myResources.getString("methodDisplayTitle");
		setDisplayTitle(displayTitle);
		updateEnvNode();
	}

	@Override
	protected void updateDefinedObject(Label label) {
		// Consider giving MethodUpdate empty constructor and set abstract's
		// EnvUpdate to it; Then call init();
		updater = new MethodUpdate(myResources, intCommand, parsingLanguage);
		updater.updateEnv(label);
	}
}