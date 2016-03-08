package view.envdisplay;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import observables.ObjectObservable;

import java.util.Arrays;

/**
 * Sub class of EnvUpdate that is responsible for taking user input and updating user defined variables.
 *
 * @author Cali
 */
public class VariableUpdate extends EnvUpdate {

    private static final String SPACE = " ";

    private String variable, newVal, text;
    private TextField tField;
    private Label title;

    /**
     * Creates a new variable update instance
     *
     * @param intCommand string observable to pass commands to command entry instance
     * @param pLang      string observable to store and set parsing language
     * @param text       text for method needing to be updated
     */
    public VariableUpdate(ObjectObservable<String> intCommand, ObjectObservable<String> pLang, String text) {
        super(intCommand, pLang);
        this.text = text;

    }

    /**
     * creates textfields needed for updater
     */
    @Override
    protected void createTextFields() {
        tField = createTextArea();
    }

    /**
     * gets the user input for the updated variable and creates and passes a command to set the variables
     * new value
     */
    @Override
    protected void setNewValues() {
        newVal = tField.getText();
        if (newVal.length() == 0) {
            return;
        }
        String toPass = getCommand(new String[]{newVal});
        newVal = variable + SPACE + newVal;
        passCommand(toPass);
        closeScene();

    }

    /**
     * creates command to pass to backend to set new values using new user input
     * values
     *
     * @param newVals String[] of new user input values
     */
    @Override
    protected String getCommand(String[] newVals) {
        String newVal = newVals[0];

        String command = super.makeCommand("MakeVariable");
        command = command + SPACE + variable + SPACE + newVal;
        return command;
    }


    /**
     * creates and adds needed components to scene
     */
    @Override
    public void updateEnv() {

        String[] splitUp = text.split(SPACE);
        this.variable = splitUp[0];
        title = createTitle("varUpdate", this.variable);
        addToScene(Arrays.asList(title, tField));

    }


}