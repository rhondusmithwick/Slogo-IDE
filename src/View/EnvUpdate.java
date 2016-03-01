package View;

import java.util.List;
import java.util.ResourceBundle;
import Observables.ObjectObservable;
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

public abstract class EnvUpdate {

    private static final int PADDING = 50;

    private static final int WIDTH = 300;
    private static final int HEIGHT = 300;
    private static final String UI_BACKGROUND_COLOR = "-fx-background-color: cornflowerblue";
    private static final String LANGUAGE_LOCATION = "resources/";
    private ObjectObservable<String> intCommand;
    private VBox vBox;
    private ResourceBundle myResources, myCommands;
    private Stage s;
    private Group root;
    private Scene scene;
    private Button setB;

    public EnvUpdate(ResourceBundle myResources, ObjectObservable<String> intCommand, ObjectObservable<String> pLang){
        this.myResources = myResources;
        myCommands = ResourceBundle.getBundle(LANGUAGE_LOCATION + pLang.get());
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
        vBox.setStyle(UI_BACKGROUND_COLOR);
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
    
    protected Label createTitle(String string) {
        Label title = new Label(string);
        title.prefWidthProperty().bind(vBox.widthProperty());
        title.setAlignment(Pos.TOP_CENTER);
        return title;
    }
    
    protected abstract String makeCommand(String[] newVals);
    
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
    
    protected String getCommand(String input){
        return myCommands.getString(input);
    }
    
    protected void passCommand(String command){
        intCommand.set(command);
    }
    protected void closeUpdater(){
        s.close();
    }

}
