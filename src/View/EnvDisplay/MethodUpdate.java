package View.EnvDisplay;

import java.util.Arrays;
import java.util.ResourceBundle;

import Observables.ObjectObservable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class MethodUpdate extends EnvUpdate {
    

    private static final char SPLITTER = '|';
    private static final String SPACE = " ";
    private String name, mNewVal, vNewVal, variables, methods, newDisp;
    private TextField vField, mField;
    private Label vTitle,mTitle, label;



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
        String toPass = makeCommand(new String[]{mNewVal, vNewVal});
        passCommand(toPass);
        closeUpdater();
        label.setText(null);
        label.setText(newDisp);

    }


    private void setValues(){
        mNewVal = mField.getText();
        if(mNewVal.length()==0){
        	mNewVal = this.methods;
        }
        vNewVal = vField.getText();
        if(vNewVal.length()==0){
        	vNewVal = this.variables;
        }
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
        newDisp = this.name + SPACE+
       		 vNewVal + SPACE + "["+ mNewVal + "]";

        return command + SPACE +newDisp;
    }







    @Override
    public void updateEnv(Label l){
    	this.label = l;
        getName(l.getText());
        getVariables(l.getText());
        getMethods(l.getText());
        System.out.println(this.name);
        mTitle = createTitle("methTitle", SPACE + name);
        vTitle = createTitle("methVarTitle",  SPACE + name); 
        addToScene(Arrays.asList(mTitle,mField,vTitle,vField));
        showScene();

    }

	private void getName(String content) {
		String[] spaceSplit = content.split(SPACE);
		this.name = spaceSplit[0];

	}
	
	private void getVariables(String content){
		int firstBrack = content.indexOf('[');
		int backBrack = content.indexOf(']');
		this.variables = content.substring(firstBrack, backBrack+1);
		vField.setText(this.variables);

	}
	
	private void getMethods(String content){
		int backBrack = content.indexOf(']');
		int secFrontBrack = (content.substring(backBrack)).indexOf('[');
		this.methods = (content.substring(backBrack)).substring(secFrontBrack+1);
		this.methods = methods.substring(0,methods.length()-1);
		mField.setText(this.methods);
	}








}
