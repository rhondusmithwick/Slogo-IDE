package view.envdisplay;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import observables.ObjectObservable;
import view.Defaults;

public class MethodDisplay extends DefinedObjectsDisplay {

	private EnvUpdate updater;

	public MethodDisplay(ObjectObservable<String> parsingLanguage, ObjectObservable<String> intCommand,
			SimpleStringProperty methods, SimpleStringProperty error) {
		super(methods, parsingLanguage, intCommand, error);
		setDisplayTitle("methodDisplayTitle");
		createCurrEnvDisp();
	}

	@Override
	protected void updateDefinedObject(Label label) {
		updater = getUpdater(Defaults.METH_UP_LOC.getDefault(), label.getText());
		updater.show();
		updater.updateLabel(label);
	}
}