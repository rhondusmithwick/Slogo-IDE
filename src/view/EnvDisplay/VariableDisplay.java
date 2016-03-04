package view.EnvDisplay;

import Observables.ObjectObservable;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;

public class VariableDisplay extends DefinedObjectsDisplay {

	private EnvUpdate updater;

	public VariableDisplay(ObjectObservable<String> pLang, ObjectObservable<String> intCommand,
			SimpleStringProperty variables, SimpleStringProperty error) {
		super(variables, pLang, intCommand, error);
		setDisplayTitle("varTitle");
		createCurrEnvDisp();
	}

	@Override
	protected void updateDefinedObject(Label label) {
		updater = getUpdater("View.EnvDisplay.VariableUpdate");
		updater.updateEnv(label);
	}
}