package view.envdisplay;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import view.Defaults;
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


    private static final int WIDTH = 300;
    private static final int HEIGHT = 600;
    private ObjectObservable<String> intCommand;
    private ResourceBundle myResources, myCommands;
    private Button setB;
	private static final char SPLITTER = '|';
	private String newVal;

    public EnvUpdate(ObjectObservable<String> intCommand, ObjectObservable<String> pLang){
    	super(WIDTH, HEIGHT, Defaults.BACKGROUND_COLOR.getDefault());
        this.myResources = ResourceBundle.getBundle(Defaults.DISPLAY_LOC.getDefault());
        myCommands = ResourceBundle.getBundle(pLang.get());
        this.intCommand=intCommand;
        
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
	
    protected String makeCommand(String key){
    	String posCommands = myCommands.getString(key);
        String command;
        int multCommands = posCommands.indexOf(SPLITTER);
        if(multCommands >0){
            command = posCommands.substring(0, multCommands);
        }else{
            command = posCommands;
        }
        return command;
    }
    
    protected abstract void setNewValues();
    
    protected abstract void updateEnv();
    
    protected void addToScene(List<Node> nodeList){
        addNodes(nodeList);
        addNodes(Arrays.asList(setB));
    }
    
    
    protected void passCommand(String command){
        intCommand.set(command);
    }


}
