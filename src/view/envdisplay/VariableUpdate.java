package view.envdisplay;

import java.util.Arrays;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import observables.ObjectObservable;

/**
 * Sub class of EnvUpdate that is responsible for taking user input and updating user defined variables.
 * @author Cali
 *
 */
public class VariableUpdate extends EnvActor {
    
    private static final String SPACE = " ";

    private String variable, newValue, text;
    private TextField textField;
    private Label title, label;

    /**
     * Creates a new variable update instance
     * @param intCommand string observable to pass commands to command entry instance
     * @param pLang string observable to store and set parsing language
     * @param text text for method needing to be updated
     */
    public VariableUpdate (ObjectObservable<String> internalCommand, ObjectObservable<String> parsingLanguage, Label label) {
        super(internalCommand, parsingLanguage);
        this.label=label;
        this.text = label.getText();
        
    }
    
    /**
     * creates textfields needed for updater
     */
    @Override
    protected void createTextFields(){
        textField = createTextArea();
    }

    /**
     * gets the user input for the updated variable and creates and passes a command to set the variables
     * new value
     */
    @Override
    protected void setNewValues() {
        newValue = textField.getText();
        if(newValue.length()==0){
            return;
        }
        String toPass = getCommand(new String[]{newValue});
        newValue = variable+SPACE+newValue;
        passCommand(toPass);
        label.setText(newValue);
        closeScene();
        
    }
    
    /**
     * creates command to pass to backend to set new values using new user input
     * values
     * @param newVals String[] of new user input values
     */
    @Override
    protected String getCommand (String[] newVals) {
        String newVal = newVals[0];

        String command =super.makeCommand("MakeVariable");
        command = command +SPACE +variable + SPACE +newVal;
        return command;
    }
 

    /**
     * creates and adds needed components to scene
     */
    @Override
    public void updateEnv(){
    
        String[] splitUp = text.split(SPACE);
        this.variable = splitUp[0];
        title = createTitle("varUpdate" , this.variable);
        addToScene(Arrays.asList(title,textField));
        
    }
    
   

    

   
    
    
}