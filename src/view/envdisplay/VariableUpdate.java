package view.envdisplay;

import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import observables.ObjectObservable;

public class VariableUpdate extends EnvUpdate {
    
    private static final String SPACE = " ";

    private String variable, newVal;
    private TextField tField;
    private Label title, label;

    public VariableUpdate (ResourceBundle myResources, ObjectObservable<String> intCommand, ObjectObservable<String> pLang, Label label) {
        super(myResources, intCommand, pLang);
        this.label=label;
        
    }
    
    @Override
    protected void createTextFields(){
        tField = createTextArea();
    }


    @Override
    protected void setNewValues() {
        newVal = tField.getText();
        if(newVal.length()==0){
            return;
        }
        String toPass = getCommand(new String[]{newVal});
        passCommand(toPass);
        closeScene();
        label.setText(null);
        label.setText(variable + SPACE + newVal);
    }
    
    @Override
    protected String getCommand (String[] newVals) {
        String newVal = newVals[0];

        String command =super.makeCommand("MakeVariable");
        command = command +SPACE +variable + SPACE +newVal;
        return command;
    }
 

    @Override
    public void updateEnv(){
    
        String[] splitUp = label.getText().split(SPACE);
        this.variable = splitUp[0];
        title = createTitle("varUpdate" , this.variable);
        addToScene(Arrays.asList(title,tField));
        
    }

    

   
    
    
}