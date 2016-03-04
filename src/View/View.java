package view;

import Observables.ObjectObservable;
import view.commentry.CommandEntry;
import view.commhistory.CommandHistoryDisplay;
import view.cons.Console;
import view.envdisplay.DefinedObjectsDisplay;
import view.envdisplay.MethodDisplay;
import view.envdisplay.VariableDisplay;
import view.error.ErrorDisplay;
import view.tbar.ToolBar;
import view.turtdisplay.TurtleDisplay;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class View implements ViewInt {
	
    private final Dimension2D turtleDispDimension;
    private final ObjectObservable<String> pLang, input, backgroundColor, intCommands, commHistory;
    private final SimpleStringProperty image = new SimpleStringProperty(this, "turtleImage");
    private final SimpleStringProperty penColor = new SimpleStringProperty(this, "penColor");
    private final SimpleStringProperty variables = new SimpleStringProperty(this, "variables");
    private final SimpleStringProperty methods = new SimpleStringProperty(this, "methods");
    private final SimpleStringProperty error = new SimpleStringProperty(this, "error");
    private final SimpleStringProperty consoleIn = new SimpleStringProperty(this, "consoleIn");
    private final SimpleObjectProperty<Point2D> location = new SimpleObjectProperty<>(this, "location");
    private final SimpleDoubleProperty heading = new SimpleDoubleProperty(this, "heading");
    private final SimpleBooleanProperty penDown = new SimpleBooleanProperty(this, "penDown");
    private BorderPane UI;
    private ResourceBundle myResources;
    private Group root;
    private ToolBar tBar;
    private Button executeButton;
    private TurtleDisplay turtDisp;
    private CommandHistoryDisplay commandHistory;
    private CommandEntry commandEntry;
    private ErrorDisplay errorDisplay;
    private DefinedObjectsDisplay vDisplay, methodsDisplay;
    private Console console;
    private HBox bottom;
    private VBox left, right;

    public View(Dimension2D turtleDispDimension, ObjectObservable<String> input, ObjectObservable<String> pLang) {
        this.pLang = pLang;
        this.input = input;
        this.turtleDispDimension = turtleDispDimension;
        this.intCommands = new ObjectObservable<>();
        this.backgroundColor = new ObjectObservable<>();
        this.commHistory = new ObjectObservable<>();
        this.myResources = ResourceBundle.getBundle(Defaults.DISPLAY_LOC.getDefault());
    
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
        setError();  
        UI.setStyle(Defaults.BACKGROUND_COLOR.getDefault());
        createTurtleDisplay();
        createToolBar();
        createBottomPane();
        createRightPane();
        createLeftPane();
        addComponents();
    }


	private void setError() {
		errorDisplay = new ErrorDisplay(error);
		errorDisplay.set();
	}


    private void addComponents() {
        UI.setCenter(turtDisp.getTurtlePane());
        UI.setRight(right);
        UI.setLeft(left);
        UI.setBottom(bottom);
        UI.setTop(tBar.getToolBarMembers());
    }


    private void createLeftPane() {
        left = new VBox(Size.VIEW_PADDING.getSize());
        BorderPane.setMargin(left, ViewInsets.LEFT.getInset());
        vDisplay = new VariableDisplay(pLang, intCommands, variables, error);
        left.getChildren().add(vDisplay.getEnvDisplay());
        methodsDisplay = new MethodDisplay(pLang, intCommands, methods, error);
        left.getChildren().add(methodsDisplay.getEnvDisplay());
        
    }


    private void createBottomPane() {
        bottom = new HBox(Size.VIEW_PADDING.getSize());
        BorderPane.setMargin(bottom, ViewInsets.BOTTOM.getInset());
      
        console = new Console(consoleIn, location, heading, penDown, penColor);
        bottom.getChildren().add(console.getConsole());
        
        commandHistory = new CommandHistoryDisplay(intCommands, commHistory);
        bottom.getChildren().add(commandHistory.getHistoryGraphic());
        
    }


    private void createToolBar() {
        tBar = new ToolBar(pLang, error, backgroundColor, image, penColor, intCommands);
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
        executeButton = new Button(myResources.getString("execute"));
        executeButton.setOnAction(e -> processExecute());
        executeButton.prefWidthProperty().bind(right.widthProperty());
        executeButton.setPrefHeight(Size.EX_BUTTON.getSize());
        right.getChildren().add(executeButton);
	}


    private void processExecute() {
        commandEntry.processCommands(); 
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
