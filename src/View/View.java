package View;

import Observables.ObjectObservable;
import View.CommEntry.CommandEntry;
import View.CommEntry.CommandEntryInterface;
import View.CommHistory.CommandHistoryDisplay;
import View.CommHistory.CommandHistoryInterface;
import View.Cons.Console;
import View.Cons.ConsoleInterface;
import View.EnvDisplay.EnvironmentDisplayInterface;
import View.EnvDisplay.MethodDisplay;
import View.EnvDisplay.VariableDisplay;
import View.Error.ErrorDisplay;
import View.Error.ErrorDisplayInterface;
import View.TBar.ToolBar;
import View.TBar.ToolBarInterface;
import View.TurtDisplay.TurtleAreaInterface;
import View.TurtDisplay.TurtleDisplay;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Arrays;
import java.util.List;

public class View implements ViewInt {

    private static final int PADDING = 10;
    private final String EXECUTE_BUTTON_LABEL = "Execute";
    private final double EXECUTE_BUTTON_HEIGHT = 20.0;
    
    private final Dimension2D turtleDispDimension;
    private final ObjectObservable<String> pLang, input, backgroundColor, intCommands, commHistory;
    private final SimpleStringProperty image = new SimpleStringProperty(this, "turtleImage");
    private final SimpleStringProperty penColor = new SimpleStringProperty(this, "penColor");
    private final SimpleStringProperty variables = new SimpleStringProperty(this, "variables");
    private final SimpleStringProperty methods = new SimpleStringProperty(this, "methods");
    private final SimpleStringProperty error = new SimpleStringProperty(this, "error");
    private final SimpleStringProperty consoleIn = new SimpleStringProperty(this, "consoleIn");
    private BorderPane UI;
    private Group root;
    private ToolBarInterface tBar;
    private Button executeButton;
    private TurtleAreaInterface turtDisp;
    private CommandHistoryInterface commandHistory;
    private CommandEntryInterface commandEntry;
    private ErrorDisplayInterface errorDisplay;
    private EnvironmentDisplayInterface vDisplay, methodsDisplay;
    private ConsoleInterface console;
    private HBox bottom;
    private VBox left, right;

    public View(Dimension2D turtleDispDimension, ObjectObservable<String> input, ObjectObservable<String> pLang) {
        this.pLang = pLang;
        this.input = input;
        this.turtleDispDimension = turtleDispDimension;
        this.intCommands = new ObjectObservable<>();
        this.backgroundColor = new ObjectObservable<>();
        this.commHistory = new ObjectObservable<>();
    
        createAppView();
    }


    private void createAppView () {
        UI = new BorderPane();
        root = new Group();
        createScene();
        root.getChildren().add(UI);
    }

    @Override
    public void bindSize (Scene scene) {
        UI.prefHeightProperty().bind(scene.heightProperty());
        UI.prefWidthProperty().bind(scene.widthProperty());
    }

    private void createScene() {
        UI.setStyle(Defaults.BACKGROUND_COLOR.getDefault());
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
        left = new VBox(PADDING);
        BorderPane.setMargin(left, ViewInsets.LEFT.getInset());
        vDisplay = new VariableDisplay(pLang, intCommands, variables);
        left.getChildren().add(vDisplay.getEnvDisplay());
        methodsDisplay = new MethodDisplay(pLang, intCommands, methods);
        left.getChildren().add(methodsDisplay.getEnvDisplay());
        
    }


    private void createBottomPane() {
        bottom = new HBox(PADDING);
        BorderPane.setMargin(bottom, ViewInsets.BOTTOM.getInset());
        errorDisplay = new ErrorDisplay(error);
        bottom.getChildren().add(errorDisplay.getErrorDisplay());
        
        console = new Console(consoleIn);
        bottom.getChildren().add(console.getConsole());
        
        commandHistory = new CommandHistoryDisplay(intCommands, commHistory);
        bottom.getChildren().add(commandHistory.getHistoryGraphic());
        
    }


    private void createToolBar() {
        tBar = new ToolBar(pLang, error, backgroundColor, image, penColor);
        BorderPane.setMargin(tBar.getToolBarMembers(), ViewInsets.TOP.getInset());
        tBar.getToolBarMembers().prefWidthProperty().bind(UI.widthProperty());
        
    }


    private void createTurtleDisplay() {
    	
        turtDisp = new TurtleDisplay(backgroundColor, turtleDispDimension);
        BorderPane.setMargin(turtDisp.getTurtlePane(), ViewInsets.TURTLE.getInset());
        

    }

    private void createRightPane() {
        right = new VBox();
        BorderPane.setMargin(right, ViewInsets.RIGHT.getInset());
        commandEntry = new CommandEntry(input, intCommands, commHistory);
        right.getChildren().add(commandEntry.getNode());
        createExecute();
    }


    private void createExecute() {
        executeButton = new Button(EXECUTE_BUTTON_LABEL);
        executeButton.setOnAction(e -> processExecute());
        executeButton.prefWidthProperty().bind(right.widthProperty());
        executeButton.setPrefHeight(EXECUTE_BUTTON_HEIGHT);
        right.getChildren().add(executeButton);
	}


    private void processExecute() {
        commandEntry.processCommands();
        vDisplay.updateEnvNode();    
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
        return Arrays.asList(image, penColor,variables, methods, error, consoleIn);
    }



    

}
