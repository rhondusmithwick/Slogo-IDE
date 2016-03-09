package view.envdisplay;

import java.util.Arrays;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import observables.ObjectObservable;

/**
 * Sub class of EnvUpdate that is responsible for taking user input and executing a user defined method.
 * @author Cali
 *
 */

public class MethodExec extends EnvActor {
   
    private static final String SPACE = " ";
    private String name, value, text;
    private TextField vField;
    private Label vTitle;
    


    /**
     * Creates a new method update instance
     * @param intCommand string observable to pass commands to command entry instance
     * @param pLang string observable to store and set parsing language
     * @param text text for method needing to be updated
     */
    public MethodExec(ObjectObservable<String> intCommand, ObjectObservable<String> pLang, Label label){
        super(intCommand, pLang);
        this.text=label.getText();
 

       
    }
    
    /**
     * creates textfields needed for updater
     */
    @Override
    public void createTextFields() {
        vField = createTextArea();
        
    }
    
    /**
     * gets the user input for the updated method and creates and passes a command to set the methods
     * new value
     */
    @Override
    protected void setNewValues() {
        setValues();
        String toPass = getCommand(new String[]{value});
        if(toPass!=null){
        	passCommand(toPass);
        }
        closeScene();
        

    }


    private void setValues(){
    	value = vField.getText();
        if(value.length()==0){
        	value = null;
        }
    }



    /**
     * creates command to pass to backend to set new values using new user input
     * values
     * @param newVals String[] of new user input values
     */
    @Override
    protected String getCommand(String[] newVals) {
    	if(newVals[0]==null){
    		return null;
    	}
        StringBuilder command = new StringBuilder();
        command.append(name+ " ");
        Arrays.asList(newVals).stream().forEach(e-> command.append(e + " ") );
        return command.toString();
    }
    
    /**
     * creates and adds needed components to scene
     */
    @Override
    public void updateEnv(){
        getName(text);
        vTitle = createTitle("methVarTitle", name); 
        addToScene(Arrays.asList(vTitle,vField));
    }

	private void getName(String content) {
		String[] spaceSplit = content.split(SPACE);
		this.name = spaceSplit[0];

	}

}
