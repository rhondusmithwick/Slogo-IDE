package View.EnvDisplay;

import java.util.Arrays;
import java.util.ResourceBundle;

import Observables.ObjectObservable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class MethodUpdate extends EnvUpdate {
    

    private static final char SPLITTER = '|';
    private static final String SPACE = " ";
    private ResourceBundle myResources;
    private String name, mNewVal, vNewVal;
    private TextField vField, mField;
    private Label label, vTitle,mTitle;
    private String[] splitUp;



    public MethodUpdate(ResourceBundle myResources, ObjectObservable<String> intCommand, ObjectObservable<String> pLang){
        super(myResources,intCommand, pLang);
        
    }

    @Override
    public void createTextFields() {
        vField = createTextArea();
        mField = createTextArea();
        
    }
    
    
    @Override
    protected void setNewValues() {
        setValues();
        if(mNewVal.length()==0 && vNewVal.length()==0){
            return;
        }
        String toPass = makeCommand(new String[]{mNewVal, vNewVal});
        passCommand(toPass);

    }


    private void setValues(){
        mNewVal = mField.getText();
        vNewVal = vField.getText();
        //if(mNewVal.equals("")){
        //}
    }





    @Override
    protected String makeCommand(String[] newVals) {
        String mNewVal = newVals[0];
        String vNewVal = newVals[1];
        String posCommands = getCommand("MakeUserInstruction");
        String command;
        int multCommands = posCommands.indexOf(SPLITTER);
        if(multCommands >0){
            command = posCommands.substring(0, multCommands);
        }else{
            command = posCommands;
        }

        return command + SPACE + "[" + vNewVal +"]" + SPACE + "["+ mNewVal + "]";
    }







    @Override
    public void updateEnv(Label l){
        this.label = l;
        splitUp = label.getText().split(SPACE);
        this.name = splitUp[0];
        mTitle = createTitle(myResources.getString("methTitle") + SPACE + name);
        vTitle = createTitle(myResources.getString("methVarTitle") + SPACE + name); 
        addToScene(Arrays.asList(mTitle,mField,vTitle,vField));
        showScene();

    }








}
