package view.envdisplay;

import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import observables.ObjectObservable;


public class MethodUpdate extends EnvUpdate {
    

    private static final char FRONT_BRACK = '[';
	private static final char BACK_BRACKET = ']';

    private static final String SPACE = " ";
    private String name, mNewVal, vNewVal, variables, methods, newDisp;
    private TextField vField, mField;
    private Label vTitle,mTitle, label;



    public MethodUpdate(ResourceBundle myResources, ObjectObservable<String> intCommand, ObjectObservable<String> pLang, Label l){
        super(myResources,intCommand, pLang);
        this.label=l;

       
    }

    @Override
    public void createTextFields() {
        vField = createTextArea();
        mField = createTextArea();
        
    }
    
    
    @Override
    protected void setNewValues() {
        setValues();
        String toPass = getCommand(new String[]{mNewVal, vNewVal});
        passCommand(toPass);
        closeScene();
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
    protected String getCommand(String[] newVals) {
        String mNewVal = newVals[0];
        String vNewVal = newVals[1];
        String command = super.makeCommand("MakeUserCommand");
        newDisp = this.name + SPACE+

       		 vNewVal + SPACE + FRONT_BRACK+ mNewVal + BACK_BRACKET;

        return command + SPACE +newDisp;
    }







    @Override
    public void updateEnv(){
        getName(label.getText());
        getVariables(label.getText());
        getMethods(label.getText());
        mTitle = createTitle("methTitle", SPACE + name);
        vTitle = createTitle("methVarTitle",  SPACE + name); 
        addToScene(Arrays.asList(mTitle,mField,vTitle,vField));
    }

	private void getName(String content) {
		String[] spaceSplit = content.split(SPACE);
		this.name = spaceSplit[0];

	}
	
	private void getVariables(String content){
		int firstBrack = content.indexOf(FRONT_BRACK);
		int backBrack = content.indexOf(BACK_BRACKET);
		this.variables = content.substring(firstBrack, backBrack+1);
		vField.setText(this.variables);

	}
	
	private void getMethods(String content){
		int backBrack = content.indexOf(BACK_BRACKET);
		int secFrontBrack = (content.substring(backBrack)).indexOf(FRONT_BRACK);
		this.methods = (content.substring(backBrack)).substring(secFrontBrack+1);
		this.methods = methods.substring(0,methods.length()-1);
		mField.setText(this.methods);
	}








}
