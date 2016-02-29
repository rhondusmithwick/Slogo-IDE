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
    private final ObjectObservable<String> language;
    private final ObjectObservable<String> input;
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


    public View(Dimension2D turtleDispDimension, ObjectObservable<String> input, ObjectObservable<String> language) {
        this.language = language;
        this.input = input;
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
        setToolBar();
        commandHistory.setCommEntry(commandEntry);
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
        vDisplay = new VariableDisplay();
        vDisplay.createEnvNode();
        vDisplay.setCommEntry(commandEntry);
        vDisplay.setPLang(tBar.getParseLang());
    }
    
    private void setMethodsDisplay() {
    	methodsDisplay = new MethodDisplay();
    	methodsDisplay.createEnvNode();
    	methodsDisplay.setCommEntry(commandEntry);
    	methodsDisplay.setPLang(tBar.getParseLang());
    }


    private void createBottomPane() {
        bottom = new HBox(BOTTOM_PADDING);

        errorDisplay = new ErrorDisplay();
        errorDisplay.createErrorDisplay();
        bottom.getChildren().add(errorDisplay.getErrorDisplay());

        console = new Console();
        console.createConsole();
        bottom.getChildren().add(console.getConsole());
        
        commandHistory = new CommandHistoryDisplay();
        commandHistory.createCommHistory();
        bottom.getChildren().add(commandHistory.getHistoryGraphic());
    }


    private void createToolBar() {
        tBar = new ToolBar(language);
        tBar.createToolBarMembers();
    }


    private void createTurtleDisplay() {
        turtDisp = new TurtleDisplay(root);
        turtDisp.createTurtleArea(turtleDispDimension);
    }

    private void createRightPane() {
        right = new VBox(3);
        Label commandEntTitle = new Label(myResources.getString("entryTitle"));
        right.getChildren().add(commandEntTitle);
        commandEntry = new CommandEntry(input);
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


    private void setToolBar() {
        tBar.setTDisp(turtDisp);
        tBar.setEDisp(errorDisplay);

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

}