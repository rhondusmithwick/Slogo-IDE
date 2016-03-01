package View;

import java.util.ResourceBundle;

import Observables.ObjectObservable;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MethodUpdate {
	
	private static final char SPLITTER = '|';
    private static final int PADDING = 15;
    private static final String SPACE = " ";
    private static final int WIDTH = 300;
    private static final int HEIGHT = 300;
    private static final int T_WIDTH = 200;
    private static final String UI_BACKGROUND_COLOR = "-fx-background-color: cornflowerblue";
    private static final String LANGUAGE_LOCATION = "resources/";
    
    private VBox vBox;
    private ResourceBundle myResources, myCommands;
    private ObjectObservable<String> intCommand;
    private Stage s;
    private Group root;
    private Scene scene;
    private String name, mNewVal, vNewVal;
    private TextField vField, mField;
    private Button setB;
    private Label label;
    private String[] splitUp;
    
    
    
    public MethodUpdate(ResourceBundle myResources, ObjectObservable<String> intCommand, ObjectObservable<String> pLang){
        this.myResources = myResources;
        myCommands = ResourceBundle.getBundle(LANGUAGE_LOCATION + pLang.get());
        this.intCommand=intCommand;
        s = new Stage();
        root = new Group();
        scene = new Scene(root, WIDTH, HEIGHT);
        createUpdater();
    }



	private void createUpdater() {
		vBox = new VBox(PADDING);
        vBox.setStyle(UI_BACKGROUND_COLOR);
        vBox.setPrefSize(WIDTH, HEIGHT);
        vBox.setAlignment(Pos.TOP_CENTER);
        root.getChildren().add(vBox);
        vField = createTextArea();
        mField = createTextArea();
        createSetButton();
		
	}



	private void createSetButton() {
		setB = new Button(myResources.getString("upButton"));
        setB.setAlignment(Pos.TOP_CENTER);
        setB.setOnAction(e->setNewValue());
		
	}



	private void setNewValue() {
		setValues();
		if(mNewVal.length()==0 && vNewVal.length()==0){
			return;
		}
		
		String toPass = makeCommand(mNewVal, vNewVal);
		intCommand.set(toPass);
		
	}
	
	
	private void setValues(){
		mNewVal = mField.getText();
		vNewVal = vField.getText();
		//if(mNewVal.equals("")){
		//}
	}






	private String makeCommand(String mNewVal2, String vNewVal2) {
		String posCommands = myCommands.getString("MakeUserInstruction");
		String command;
        int multCommands = posCommands.indexOf(SPLITTER);
        if(multCommands >0){
            command = posCommands.substring(0, multCommands);
        }else{
            command = posCommands;
        }
        
		return command + SPACE + "[" + vNewVal +"]" + SPACE + "["+ mNewVal + "]";
	}



	private TextField createTextArea() {
		
		TextField tField = new TextField();
        tField.setPrefWidth(T_WIDTH);
        return tField;
	}



	private Label createTitle(String string) {
        Label title = new Label(string);
        title.setMaxWidth(WIDTH);
        title.setAlignment(Pos.TOP_CENTER);
        vBox.getChildren().add(title);
        return title;
	}
	   public void updateMethod(Label l){
	        this.label = l;
	        splitUp = label.getText().split(SPACE);
	        this.name = splitUp[0];
	        createTitle(myResources.getString("methTitle") + SPACE + name);
	        vBox.getChildren().add(mField);
	        createTitle(myResources.getString("methVarTitle") + SPACE + name); 
	        vBox.getChildren().add(vField);
	        vBox.getChildren().add(setB);
	        s.setScene(scene);
	        s.show();
	        
	    }
	
	
	
	
}
