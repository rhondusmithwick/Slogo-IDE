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

	private EnvActor updater;

	/**
	 * Creates new Method display instance
	 * @param parsingLanguage observable string containing parsing language
	 * @param intCommand observable string to pass commands to command entry instance
	 * @param methods simplestring property storing user defined methods
	 * @param error simplestringproperty to pass errors to be shown
	 */
	public MethodDisplay(ObjectObservable<String> parsingLanguage, ObjectObservable<String> internalCommand,
			SimpleStringProperty methods, SimpleStringProperty error) {
		super(methods, parsingLanguage, internalCommand, error);
		setDisplayTitle("methodDisplayTitle");
		createCurrEnvDisp();
	}

	/**
	 * creates a new updater object that updates a label's text based on user input
	 * @param Label label whose text is to be updated
	 */
	@Override
	protected void updateDefinedObject(Label label) {
		updater = getUpdater(Defaults.METH_ACT_LOC.getDefault(), label);
		updater.show();
	}
	
	protected void parseString(String text){
		String[] split = text.split("=");
		split[0] = split[0].substring(1);
		//System.out.println("this is text " + text);
		//System.out.println("this is split 1 " + split[0]);
		//System.out.println("this is split 2 " + split[1]);
		if(split[1].endsWith("}")){
			split[1] = split[1].substring(0, split[1].length()-1);
		}
		setLabel(split[0] + " " + split[1]);
	}
	
	
}