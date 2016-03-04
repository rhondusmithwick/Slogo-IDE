package view.EnvDisplay;

import java.util.Arrays;
import java.util.ResourceBundle;
import Observables.ObjectObservable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class VariableUpdate extends EnvUpdate {
    
    private static final char SPLITTER = '|';
    private static final String SPACE = " ";

    private String variable, newVal;
    private TextField tField;
    private Label title, label;

    public VariableUpdate (ResourceBundle myResources, ObjectObservable<String> intCommand, ObjectObservable<String> pLang) {
        super(myResources, intCommand, pLang);
        
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
        String toPass = makeCommand(new String[]{newVal});
        passCommand(toPass);
        closeUpdater();
        label.setText(null);
        label.setText(variable + SPACE + newVal);
    }
    
    @Override
    protected String makeCommand (String[] newVals) {
        String newVal = newVals[0];
        String posCommands = getCommand("MakeVariable");
        String command;
        int multCommands = posCommands.indexOf(SPLITTER);
        if(multCommands >0){
            command = posCommands.substring(0, multCommands);
        }else{
            command = posCommands;
        }
        command = command +SPACE +variable + SPACE +newVal;
        return command;
    }
 

    @Override
    public void updateEnv(Label l){
        this.label = l;
        String[] splitUp = l.getText().split(SPACE);
        this.variable = splitUp[0];;
        title = createTitle("varUpdate" , this.variable);
        addToScene(Arrays.asList(title,tField));
        showScene();
        
    }

    

   
    
    
}