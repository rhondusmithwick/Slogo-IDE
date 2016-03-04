package view.envdisplay;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import observables.ObjectObservable;

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
		updater = getUpdater("view.envdisplay.VariableUpdate", label);
		updater.show();
	}
}