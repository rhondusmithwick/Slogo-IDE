package view.envdisplay;

import java.util.List;
import java.util.ResourceBundle;

import view.Defaults;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import observables.ObjectObservable;

public abstract class EnvUpdate {

    private static final int PADDING = 50;

    private static final int WIDTH = 300;
    private static final int HEIGHT = 600;
    private ObjectObservable<String> intCommand;
    private VBox vBox;
    private ResourceBundle myResources, myCommands;
    private Stage s;
    private Group root;
    private Scene scene;
    private Button setB;
	private static final char SPLITTER = '|';

    public EnvUpdate(ResourceBundle myResources, ObjectObservable<String> intCommand, ObjectObservable<String> pLang){
        this.myResources = myResources;
        myCommands = ResourceBundle.getBundle(Defaults.RESOURCE_LOCATION.getDefault() + pLang.get());
        this.intCommand=intCommand;
        s = new Stage();
        root = new Group();
        scene = new Scene(root, WIDTH, HEIGHT);
        createVBox();
        createSetButton();
        createTextFields();
    }


    protected abstract void createTextFields ();

    private void createVBox () {
        vBox = new VBox(PADDING);
        vBox.setStyle(Defaults.BACKGROUND_COLOR.getDefault());
        vBox.prefWidthProperty().bind(scene.widthProperty());
        vBox.prefHeightProperty().bind(scene.heightProperty());
        vBox.setAlignment(Pos.TOP_CENTER);
        root.getChildren().add(vBox);

    }

    private void createSetButton() {
        setB = new Button(myResources.getString("upButton"));
        setB.setAlignment(Pos.TOP_CENTER);
        setB.setOnAction(e->setNewValues());

    }

    protected TextField createTextArea() {
        TextField tField = new TextField();
        tField.prefWidthProperty().bind(vBox.widthProperty());
        VBox.setVgrow(tField, Priority.ALWAYS);
        return tField;
    }
    


	protected Label createTitle(String titleName, String other) {
        Label title = new Label(myResources.getString(titleName)+other);
        title.prefWidthProperty().bind(vBox.widthProperty());
        title.setAlignment(Pos.TOP_CENTER);
        return title;
    }
    
    protected String makeCommand(String[] newVals, String key){
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
    
    public abstract void updateEnv(Label label);
    
    protected void addToScene(List<Node> nodeList){
        vBox.getChildren().addAll(nodeList);
        vBox.getChildren().add(setB);
    }
    
    public void showScene(){
        s.setScene(scene);
        s.show();
    }
    
    
    protected void passCommand(String command){
        intCommand.set(command);
    }
    protected void closeUpdater(){
        s.close();
    }

}
