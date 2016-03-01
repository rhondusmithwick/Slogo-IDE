package View;


import Observables.ObjectObservable;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class View implements ViewInt {


    private static final String UI_BACKGROUND_COLOR = "-fx-background-color: cornflowerblue";
    private static final int BOTTOM_PADDING =20;
    private static final String DEFAULT_LANGUAGE = "english";
    private static final String DEFAULT_LOCATION = "resources/guiStrings/";
    private static final String DISP = "disp";
    private final String EXECUTE_BUTTON_LABEL = "Execute";
    private final double EXECUTE_BUTTON_HEIGHT = 20.0;
    private final double EXECUTE_BUTTON_WIDTH = 200.0;
    private final Dimension2D turtleDispDimension;
    private final ObjectObservable<String> pLang, input, error, consoleInput, backgroundColor, intCommands;
    private ResourceBundle myResources;
    private BorderPane UI;
    private Group root;
    private ToolBarInterface tBar;
    private Button executeButton;
    private TurtleAreaInterface turtDisp;
    private CommHistory commandHistory;
    private CommandEntryInterface commandEntry;
    private ErrorDisplayInterface errorDisplay;
    private EnvironmentDisplayInterface vDisplay, methodsDisplay;
    private ConsoleInterface console;
    private HBox bottom;
    private VBox left, right;


    public View(Dimension2D turtleDispDimension, ObjectObservable<String> input, ObjectObservable<String> pLang) {
        this.pLang = pLang;
        this.input = input;
        intCommands = new ObjectObservable<>();
        error = new ObjectObservable<>();
        consoleInput = new ObjectObservable<>();
        backgroundColor = new ObjectObservable<>();
        myResources = ResourceBundle.getBundle(DEFAULT_LOCATION + DEFAULT_LANGUAGE + DISP);
        this.turtleDispDimension = turtleDispDimension;
        UI = new BorderPane();
        root = new Group();
        createScene();
        root.getChildren().add(UI);
        


    }


    private void createScene() {
        UI.setStyle(UI_BACKGROUND_COLOR);
        createTurtleDisplay();
        createToolBar();
        createBottomPane();
        createRightPane();
        createLeftPane();
        addComponents();
    }


    private void addComponents() {
        UI.setCenter(turtDisp.getTurtlePane());
        UI.setRight(right);
        UI.setLeft(left);
        UI.setBottom(bottom);
        UI.setTop(tBar.getToolBarMembers());
    }


    private void createLeftPane() {
        left = new VBox();
        setVDisplay();
        left.getChildren().add(vDisplay.getEnvDisplay());
        setMethodsDisplay();
        left.getChildren().add(methodsDisplay.getEnvDisplay());
    }


    private void setVDisplay () {
        vDisplay = new VariableDisplay(pLang, intCommands);
        vDisplay.createEnvNode();

    }
    
    private void setMethodsDisplay() {
    	methodsDisplay = new MethodDisplay(pLang, intCommands);
    	methodsDisplay.createEnvNode();

    }


    private void createBottomPane() {
        bottom = new HBox(BOTTOM_PADDING);

        errorDisplay = new ErrorDisplay();
        errorDisplay.createErrorDisplay(error);
        bottom.getChildren().add(errorDisplay.getErrorDisplay());

        console = new Console(consoleInput);
        console.createConsole();
        bottom.getChildren().add(console.getConsole());
        
        commandHistory = new CommandHistoryDisplay(intCommands);
        commandHistory.createCommHistory();
        bottom.getChildren().add(commandHistory.getHistoryGraphic());
    }


    private void createToolBar() {
        tBar = new ToolBar(pLang, error, backgroundColor);
        tBar.createToolBarMembers();
    }


    private void createTurtleDisplay() {
        turtDisp = new TurtleDisplay(root, backgroundColor);
        turtDisp.createTurtleArea(turtleDispDimension);
    }

    private void createRightPane() {
        right = new VBox(3);
        Label commandEntTitle = new Label(myResources.getString("entryTitle"));
        right.getChildren().add(commandEntTitle);
        commandEntry = new CommandEntry(input, intCommands);
        commandEntry.createEntryBox();
        right.getChildren().add(commandEntry.getTextBox());
        createExecute();
    }


    private void createExecute() {
        executeButton = new Button(EXECUTE_BUTTON_LABEL);
        executeButton.setPrefSize(EXECUTE_BUTTON_WIDTH, EXECUTE_BUTTON_HEIGHT);
        executeButton.setOnAction(e -> processExecute());
        right.getChildren().add(executeButton);

	}


    private void processExecute() {
        commandHistory.addCommand(commandEntry.getTextBox().getText());
        commandEntry.getBoxCommands();
        vDisplay.updateEnvNode();
        commandEntry.clearCommands();
        
    }
    
    @Override
    public Group getGroup() {
        return root;
    }


    @Override
    public Group getInnerGroup() {
        return turtDisp.getTurtleArea();
    }

    @Override
    public List<SimpleStringProperty> getProperties() {
        List<SimpleStringProperty> tProps = tBar.getProperties();
        return Arrays.asList(tProps.get(0), tProps.get(1),vDisplay.getEnvProperty());
    }
    
    @Override public List<ObjectObservable<String>> getObservables(){
        return Arrays.asList(error, consoleInput);
    }

}
