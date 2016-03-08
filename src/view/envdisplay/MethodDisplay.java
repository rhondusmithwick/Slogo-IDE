package view.envdisplay;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import observables.ObjectObservable;
import view.Defaults;

/**
 * sub class of defined objects display responsible for showing user defined methods
 * and for starting the updating process when user methods are clicked
 * @author calinelson and stephen kwok
 *
 */
public class MethodDisplay extends DefinedObjectsDisplay {

	private EnvUpdate updater;

	/**
	 * Creates new Method display instance
	 * @param parsingLanguage observable string containing parsing language
	 * @param intCommand observable string to pass commands to command entry instance
	 * @param methods simplestring property storing user defined methods
	 * @param error simplestringproperty to pass errors to be shown
	 */
	public MethodDisplay(ObjectObservable<String> parsingLanguage, ObjectObservable<String> intCommand,
			SimpleStringProperty methods, SimpleStringProperty error) {
		super(methods, parsingLanguage, intCommand, error);
		setDisplayTitle("methodDisplayTitle");
		createCurrEnvDisp();
	}

	/**
	 * creates a new updater object that updates a label's text based on user input
	 * @param Label label whose text is to be updated
	 */
	@Override
	protected void updateDefinedObject(Label label) {
		updater = getUpdater(Defaults.METH_UP_LOC.getDefault(), label);
		updater.show();
	}
}