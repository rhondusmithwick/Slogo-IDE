package view.tbar;

import java.util.Arrays;
import view.envdisplay.EnvUpdate;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import observables.ObjectObservable;

/**
 * INSERT CLASS DESCRIPTION
 * 
 * @author Stephen
 *
 */

public class PenSizeUpdater extends EnvUpdate {
	
	
	private static final String SPACE = " ";
    private TextField tField;
    private String newVal;

    public PenSizeUpdater(ObjectObservable<String> language, ObjectObservable<String> intCommand){
    	super(intCommand, language);
    }

	@Override
	protected void createTextFields() {
		tField = createTextArea();
		
	}


	@Override
	protected String getCommand(String[] newVals) {
		String command = makeCommand("SetPenSize");
		return command + SPACE + newVals[0];
		
	}

	@Override
	protected void setNewValues() {
		closeScene();
		newVal = tField.getText();
    	if (newVal.length() == 0){
    	   return;
    	}
    	passCommand(getCommand(new String[] {newVal}));
		
	}

	@Override
	protected void updateEnv() {
		Label title = createTitle("setPenSize", "");
		createTextFields();
		addToScene(Arrays.asList(title, tField));
		
	}

}
