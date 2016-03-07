package view.envdisplay;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import view.Defaults;
import view.Size;
import view.utilities.GetCommand;
import view.utilities.PopUp;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import observables.ObjectObservable;

public abstract class EnvUpdate extends PopUp{

    private ObjectObservable<String> intCommand;
    private ResourceBundle myResources;
    private Button setB;
	private String newVal;
    private ObjectObservable<String> pLang;

    public EnvUpdate(ObjectObservable<String> intCommand, ObjectObservable<String> pLang){
    	super(Size.ENV_WIDTH.getSize(), Size.ENV_HEIGHT.getSize(), Defaults.BACKGROUND_COLOR.getDefault());
        this.myResources = ResourceBundle.getBundle(Defaults.DISPLAY_LOC.getDefault());
        this.intCommand=intCommand;
        this.pLang = pLang;
        
    }

    @Override
    protected void createScene(){
    	createSetButton();
        createTextFields();
        updateEnv();
    }
    

    protected abstract void createTextFields ();
    
    public void updateLabel(Label l){
    	l.setText(newVal);
    }


    private void createSetButton() {
        setB = new Button(myResources.getString("upButton"));
        setB.setAlignment(Pos.TOP_CENTER);
        setB.setOnAction(e->setNewValues());

    }

    protected TextField createTextArea() {
        TextField tField = new TextField();
        tField.prefWidthProperty().bind(getSize(false));
        VBox.setVgrow(tField, Priority.ALWAYS);
        return tField;
    }
    


	protected Label createTitle(String titleName, String other) {
        Label title = new Label(myResources.getString(titleName)+other);
        title.prefWidthProperty().bind(getSize(false));
        title.setAlignment(Pos.TOP_CENTER);
        return title;
    }
    
	protected abstract String getCommand(String[] newVals);
	
    
    protected abstract void setNewValues();
    
    protected abstract void updateEnv();
    
    protected void addToScene(List<Node> nodeList){
        addNodes(nodeList);
        addNodes(Arrays.asList(setB));
    }
    
    
    protected void passCommand(String command){
        intCommand.set(command);
    }

    protected String makeCommand (String key) {
        return GetCommand.makeCommand(key,pLang.get() );
    }


}
