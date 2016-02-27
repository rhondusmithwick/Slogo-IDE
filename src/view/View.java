package view;


import Controller.Controller.StringObservable;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.List;
import java.util.ResourceBundle;

public class View implements ViewInt {


    private static final String UI_BACKGROUND_COLOR = "-fx-background-color: cornflowerblue";
    private static final int LEFT_HEIGHT = 400;
    private static final int LEFT_WIDTH = 100;
    private static final int BOTTOM_PADDING = 50;
    private static final String DEFAULT_LANGUAGE = "english";
    private static final String DEFAULT_LOCATION = "resources/guiStrings/";
    private static final String DISP = "disp";
    private final String EXECUTE_BUTTON_LABEL = "Execute";
    private final double EXECUTE_BUTTON_HEIGHT = 20.0;
    private final double EXECUTE_BUTTON_WIDTH = 200.0;
    private final Dimension2D turtleDispDimension;
    private final StringObservable language;
    private final StringObservable input;
    private ResourceBundle myResources;
    private BorderPane UI;
    private Group root;
    private TurtleAreaInterface turtDisp;
    private ToolBar tBar;
    private Button executeButton;
    private CommHistory commandHistory;
    private CommandEntryInterface commandEntry;
    private ErrorDisplayInterface errorDisplay;
    private HBox bottom;
    private VBox left, right;
    private Node commandHistoryBox, entryBox;

    public View(Dimension2D turtleDispDimension, StringObservable input, StringObservable language) {
        this.language = language;
        this.input = input;
        myResources = ResourceBundle.getBundle(DEFAULT_LOCATION + "english" + DISP);
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
        createLeftPane();
        createRightPane();
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
        Rectangle r = new Rectangle(LEFT_WIDTH, LEFT_HEIGHT);
        r.setFill(Color.CORNFLOWERBLUE);
        left.getChildren().add(r);
    }


    private void createBottomPane() {
        bottom = new HBox(BOTTOM_PADDING);

        errorDisplay = new ErrorDisplay();
        errorDisplay.createErrorDisplay();
        bottom.getChildren().add(errorDisplay.getErrorDisplay());

        commandHistory = new CommandHistoryDisplay();
        commandHistory.createCommHistory();
        commandHistoryBox = commandHistory.getHistoryGraphic();
        bottom.getChildren().add(commandHistoryBox);
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
        right = new VBox();
        Label commandEntTitle = new Label(myResources.getString("entryTitle"));
        right.getChildren().add(commandEntTitle);
        commandEntry = new CommandEntry(input);
        commandEntry.createEntryBox();
        entryBox = commandEntry.getTextBox();
        right.getChildren().add(entryBox);
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
        commandEntry.clearCommands();
    }


    private void setToolBar() {
        tBar.setTDisp(turtDisp);
        tBar.setEDisp(errorDisplay);

    }


    public void passError(String Error) {
        // TODO Auto-generated method stub

    }


    public void passInput(String command) {

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
        return tBar.getProperties();
    }

}
