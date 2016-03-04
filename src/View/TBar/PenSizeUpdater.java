package View.TBar;

import java.util.ResourceBundle;

import Observables.ObjectObservable;
import View.Defaults;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * INSERT CLASS DESCRIPTION
 * 
 * @author Stephen
 *
 */

public class PenSizeUpdater {
	
	private static final int PADDING = 50;

    private static final int WIDTH = 300;
    private static final int HEIGHT = 600;
    private static final char SPLITTER = '|'; 
	private static final String SPACE = " ";
    private ObjectObservable<String> intCommand;
    private VBox vBox;
    private ResourceBundle myResources, myCommands;
    private Stage s;
    private Group root;
    private Scene scene;
    private Button setButton;
    TextField tField;

    public PenSizeUpdater(ObjectObservable<String> language, ObjectObservable<String> intCommand){
    	myResources = ResourceBundle.getBundle(Defaults.DISPLAY_LOC.getDefault());
    	myCommands = ResourceBundle.getBundle(language.get());
        s = new Stage();
        root = new Group();
        scene = new Scene(root, WIDTH, HEIGHT);
        createVBox();
        createTextArea();
        createSetButton();
        showScene();
    }

    private void createVBox () {
        vBox = new VBox(PADDING);
        vBox.setStyle(Defaults.BACKGROUND_COLOR.getDefault());
        vBox.prefWidthProperty().bind(scene.widthProperty());
        vBox.prefHeightProperty().bind(scene.heightProperty());
        vBox.setAlignment(Pos.TOP_CENTER);
        root.getChildren().add(vBox);
    }

    private void createSetButton() {
        setButton = new Button(myResources.getString("upButton"));
        setButton.setAlignment(Pos.TOP_CENTER);
        setButton.setOnAction(e->retrieveUserInput());
        vBox.getChildren().add(setButton);
    }
    
    private void retrieveUserInput() {
    	String userInput = tField.getText();
    	String command = myCommands.getString("SetPenSize");
    	String commandToPass = makeCommand(command);
    	passCommand(commandToPass + SPACE + userInput);
    	closeUpdater();
    }

    protected TextField createTextArea() {
        TextField tField = new TextField();
        tField.prefWidthProperty().bind(vBox.widthProperty());
        VBox.setVgrow(tField, Priority.ALWAYS);
        vBox.getChildren().add(tField);
        return tField;
    }

	protected Label createTitle(String titleName, String other) {
        Label title = new Label(myResources.getString(titleName)+other);
        title.prefWidthProperty().bind(vBox.widthProperty());
        title.setAlignment(Pos.TOP_CENTER);
        return title;
    }
    
    public void showScene(){
        s.setScene(scene);
        s.show();
    }
    
    protected String getCommand(String input){
        return myCommands.getString(input);
    }
    
    protected void passCommand(String command){
        intCommand.set(command);
    }
    protected void closeUpdater(){
        s.close();
    }
    
    protected String makeCommand(String key) {
        String retrievedString = myCommands.getString(key);
        int splitterPos = retrievedString.indexOf(SPLITTER);
        if (splitterPos > 0) {
            return retrievedString.substring(0, splitterPos);
        } else {
            return retrievedString;
        }
    }

}
