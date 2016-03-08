package view.envdisplay;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import observables.ObjectObservable;
import view.Defaults;

/**
 * class reponsible for displaying user defined variables and starting user updating process. is subclass
 * of defined objects display abstract class.
 * @author calisnelson and Stephen Kwok
 *
 */

public class VariableDisplay extends DefinedObjectsDisplay {

	private EnvUpdate updater;
	
	/**
	 * creates new variable display instance
	 * @param pLang string observable for storing and setting parsing language
	 * @param intCommand string observable to pass commands to command entry instance
	 * @param variables simplestring property storing user defined variables
	 * @param error simplestring property to display error
	 */
	public VariableDisplay(ObjectObservable<String> pLang, ObjectObservable<String> intCommand,
			SimpleStringProperty variables, SimpleStringProperty error) {
		super(variables, pLang, intCommand, error);
		setDisplayTitle("varTitle");
		createCurrEnvDisp();
	}

	/**
         * creates a new updater object that updates a label's text based on user input
         * @param Label label whose text is to be updated
         */
	@Override
	protected void updateDefinedObject(Label label) {
		updater = getUpdater(Defaults.VAR_UP_LOC.getDefault(), label);
		updater.updateLabel(label);
	}
}