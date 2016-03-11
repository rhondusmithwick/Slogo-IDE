package view.tbar.popupdisplays;

import java.util.Arrays;

import view.envdisplay.EnvActor;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import observables.ObjectObservable;

/**
 * Class responsible for creating popup to allow user to modify pen size.
 * Is a subclass of the envupdate abstract class.
 * 
 * @author Stephen
 *
 */

public class PenSizeUpdater extends EnvActor{


    private static final String SPACE = " ";
    private TextField textField;
    
    /**
     * creates new pensizeupdater instance
     * @param language string observable for setting and getting parsing language
     * @param intCommand string observable to pass commands to command entry instance to pass to backend
     */
    public PenSizeUpdater(ObjectObservable<String> language, ObjectObservable<String> internalCommand){
        super(internalCommand, language);
    }
    
    /**
     * creates text fields needed for updating the pen size 
     */
    @Override
    protected void createTextFields() {
        textField = createTextArea();

    }

    /**
     * creates command to update pensize with new value
     * @param newVals string[] with new value for pensize at 0th index
     * @return created command to update pensize that can be passed to backend by command entry instance
     */
    @Override
    protected String getCommand(String[] newValues) {
        String command = makeCommand("SetPenSize");
        return command + SPACE + newValues[0];

    }

    /**
     * Gets new values that the user set. If no value set no changes are made, but
     * if new value is set it creates a command to set pen size and passes it to 
     * the backend via the command entry instance
     */
    @Override
    protected void setNewValues() {
        closeScene();
        String newValue = textField.getText();
        if (newValue.length() == 0){
            return;
        }
        passCommand(getCommand(new String[] {newValue}));

    }
    
    /**
     * creates needed elements for updater and then adds them to the popups scene
     */
    @Override
    public void updateEnv() {
        Label title = createTitle("setPenSize", "");
        createTextFields();
        addToScene(Arrays.asList(title, textField));
    }

}
