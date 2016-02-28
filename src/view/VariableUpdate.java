package view;

import java.util.ResourceBundle;
import Controller.Controller.StringObservable;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VariableUpdate {
    
    private static final char SPLITTER = '|';
    private static final int PADDING = 75;
    private static final String SPACE = " ";
    private static final int WIDTH = 300;
    private static final int HEIGHT = 300;
    private static final int T_WIDTH = 200;
    private static final String UI_BACKGROUND_COLOR = "-fx-background-color: cornflowerblue";
    private static final String LANGUAGE_LOCATION = "resources/";
    
    private VBox vBox;
    private ResourceBundle myResources, myCommands;
    private CommandEntryInterface cEnt;
    private Stage s;
    private Group root;
    private Scene scene;
    private String variable, newVal;
    private TextField tField;
    private Button setB;
    private Label label;

    public VariableUpdate (ResourceBundle myResources, CommandEntryInterface cEnt, StringObservable pLang) {
        this.myResources = myResources;
        myCommands = ResourceBundle.getBundle(LANGUAGE_LOCATION + pLang.get());
        this.cEnt = cEnt;
        s = new Stage();
        root = new Group();
        scene = new Scene(root, WIDTH, HEIGHT);
        createUpdater();
    }

    private void createUpdater () {
        vBox = new VBox(PADDING);
        vBox.setStyle(UI_BACKGROUND_COLOR);
        vBox.setPrefSize(WIDTH, HEIGHT);
        vBox.setAlignment(Pos.TOP_CENTER);
        root.getChildren().add(vBox);
        

        createTextArea();
        createSetButton();
    }

    private void createSetButton () {
        setB = new Button(myResources.getString("varButton"));
        setB.setAlignment(Pos.TOP_CENTER);
        setB.setOnAction(e->setNewValue());
        
    }

    private void setNewValue() {
        newVal = tField.getText();
        if(newVal.length()==0){
            return;
        }
        String toPass = createMakeCommand(newVal);
        cEnt.passInternalCommands(toPass);
        label.setText(variable + SPACE + newVal);
        s.close();
        
        
    }

    private String createMakeCommand (String newVal) {
        String posCommands = myCommands.getString("MakeVariable");
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

    private void createTextArea () {
        tField = new TextField();
        tField.setPrefWidth(T_WIDTH);
        
    }

    private void createTitle () {
        Label title = new Label(myResources.getString("varUpdate") + this.variable);
        title.setMaxWidth(WIDTH);
        title.setAlignment(Pos.TOP_CENTER);
        vBox.getChildren().add(title);
        
    }
    
    public void updateVariable(Label l){
        this.label = l;
        String[] splitUp = l.getText().split(SPACE);
        this.variable = splitUp[0];;
        createTitle();
        vBox.getChildren().add(tField);
        vBox.getChildren().add(setB);
        s.setScene(scene);
        s.show();
        
    }
    

   
    
    
}