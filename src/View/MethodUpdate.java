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
    private CommandEntryInterface cEnt;
    private Stage s;
    private Group root;
    private Scene scene;
    private String variable, newVal;
    private TextField vField, mField;
    private Button setB;
    private Label label, titleV, titleM;
    
    
    
    public MethodUpdate(ResourceBundle myResources, CommandEntryInterface cEnt, ObjectObservable<String> pLang){
        this.myResources = myResources;
        myCommands = ResourceBundle.getBundle(LANGUAGE_LOCATION + pLang.get());
        this.cEnt = cEnt;
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
        
        titleV = createTitle(myResources.getString("upMethVar"));
        vField = createTextArea();
        titleM = createTitle (myResources.getString("upMeth"));
        mField = createTextArea();
        createSetButton();
		
	}



	private void createSetButton() {
		setB = new Button(myResources.getString("upButton"));
        setB.setAlignment(Pos.TOP_CENTER);
        setB.setOnAction(e->setNewValue());
		
	}



	private Object setNewValue() {
		// TODO Auto-generated method stub
		return null;
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
	
	
	
	
}
