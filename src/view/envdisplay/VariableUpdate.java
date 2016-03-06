package view.envdisplay;

import java.util.Arrays;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import observables.ObjectObservable;

public class VariableUpdate extends EnvUpdate {
    
    private static final String SPACE = " ";

    private String variable, newVal, text;
    private TextField tField;
    private Label title;

    public VariableUpdate (ObjectObservable<String> intCommand, ObjectObservable<String> pLang, String text) {
        super(intCommand, pLang);
        this.text = text;
        
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
        newVal = variable+SPACE+newVal;
        passCommand(toPass);
        closeScene();
        
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
    
        String[] splitUp = text.split(SPACE);
        this.variable = splitUp[0];
        title = createTitle("varUpdate" , this.variable);
        addToScene(Arrays.asList(title,tField));
        
    }


    

   
    
    
}