package view;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.GlobalProperties;
import main.Slogo;
import observables.ObjectObservable;
import view.bottom.commhistory.CommandHistoryDisplay;
import view.bottom.turtparams.TurtleParams;
import view.commentry.CommandEntry;
import view.envdisplay.DefinedObjectsDisplay;
import view.envdisplay.MethodDisplay;
import view.envdisplay.VariableDisplay;
import view.error.ErrorDisplay;
import view.tbar.BottomBar;
import view.tbar.SubBar;
import view.tbar.TopBar;
import view.turtdisplay.TurtleDisplay;
import view.utilities.ButtonFactory;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author calinelson and stephen kwok This class represents a single workspace
 *         for the slogo interpreter, and is repsonsible for displaying and
 *         managing all componenets of the larger view;
 */
public class View implements ViewInt {

    private final Dimension2D turtleDispDimension;
    private final ObjectObservable<String> internalCommand, commandHistInput;
    private final ObjectObservable<Integer> selectedTurtle;
    private final SimpleStringProperty image = new SimpleStringProperty(this, "turtleImage");
    private final SimpleStringProperty variables = new SimpleStringProperty(this, "variables");
    private final SimpleStringProperty definedCommands = new SimpleStringProperty(this, "definedCommands");
    private final SimpleStringProperty error = new SimpleStringProperty(this, "error");
    private final SimpleStringProperty turtleIDs = new SimpleStringProperty(this, "turtleIDs");
    private BorderPane UI;
    private final ResourceBundle myResources;
    private Group root;
    private TurtleDisplay turtleDisplay;
    private CommandEntry commandEntry;
    private HBox bottom;
    private VBox left, right, top;
    private final Slogo slogo;
    private final GlobalProperties globalProperties;

    /**
     * creates a new view object
     *
     * @param turtleDispDimension 2D dimension defining the turtle area size
     * @param input               observable string used to pass input to back end
     * @param pLang               observable string used to set parsing language on backend
     */
    public View(GlobalProperties globalProperties, Dimension2D turtleDispDimension, Slogo slogo) {
        this.slogo = slogo;
        this.globalProperties = globalProperties;
        this.turtleDispDimension = turtleDispDimension;
        this.internalCommand = new ObjectObservable<>();
        this.selectedTurtle = new ObjectObservable<>();
        this.commandHistInput = new ObjectObservable<>();
        this.myResources = ResourceBundle.getBundle(Defaults.DISPLAY_LOC.getDefault());
        createAppview();
        setPreferences();

    }

    private void setPreferences() {
        PreferenceSetter pSet = new PreferenceSetter(globalProperties, internalCommand);
        pSet.setPreferences();

    }

    private void createAppview() {
        UI = new BorderPane();
        root = new Group();
        createScene();
        root.getChildren().add(UI);
    }

    /**
     * binds the size of the border pane to the size of the application
     *
     * @param scene to bind the panes size to
     */
    @Override
    public void bindSize(ReadOnlyDoubleProperty height, ReadOnlyDoubleProperty width) {
        UI.prefHeightProperty().bind(height.subtract(Size.TOP_TAB.getSize()));
        UI.prefWidthProperty().bind(width);
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
        ErrorDisplay errorDisplay = new ErrorDisplay(error);
        errorDisplay.set();
    }

    private void addComponents() {
        UI.setCenter(turtleDisplay.getTurtlePane());
        UI.setRight(right);
        UI.setLeft(left);
        UI.setBottom(bottom);
        UI.setTop(top);
    }

    private void createLeftPane() {
        left = new VBox(Size.VIEW_PADDING.getSize());
        BorderPane.setMargin(left, ViewInsets.LEFT.getInset());
        DefinedObjectsDisplay variableDisplay = new VariableDisplay(globalProperties, internalCommand, variables, error);
        left.getChildren().add(variableDisplay.getEnvDisplay());
        DefinedObjectsDisplay methodsDisplay = new MethodDisplay(globalProperties, internalCommand, definedCommands, error);
        left.getChildren().add(methodsDisplay.getEnvDisplay());

    }

    private void createBottomPane() {
        bottom = new HBox(Size.VIEW_PADDING.getSize());
        BorderPane.setMargin(bottom, ViewInsets.BOTTOM.getInset());

        TurtleParams turtleParameters = new TurtleParams(selectedTurtle);
        bottom.getChildren().add(turtleParameters.getTurtleParams());

        CommandHistoryDisplay commandHistory = new CommandHistoryDisplay(internalCommand, commandHistInput);
        bottom.getChildren().add(commandHistory.getHistoryGraphic());

    }


    private void createToolBar() {
        top = new VBox(Size.TB_PADDING.getSize());
        SubBar topBar = new TopBar(globalProperties, turtleIDs, internalCommand, selectedTurtle, error);
        SubBar bottomBar = new BottomBar(globalProperties, internalCommand, error, slogo);
        top.getChildren().addAll(topBar.getContainer(), bottomBar.getContainer());
        BorderPane.setMargin(top, ViewInsets.TOP.getInset());

    }

    private void createTurtleDisplay() {

        turtleDisplay = new TurtleDisplay(globalProperties, turtleDispDimension);
        BorderPane.setMargin(turtleDisplay.getTurtlePane(), ViewInsets.TURTLE.getInset());

    }

    private void createRightPane() {
        right = new VBox();
        BorderPane.setMargin(right, ViewInsets.RIGHT.getInset());
        commandEntry = new CommandEntry(globalProperties, internalCommand, commandHistInput);
        right.getChildren().add(commandEntry.getNode());
        createExecute();
    }

    private void createExecute() {
        Button executeButton = ButtonFactory.createButton(myResources.getString("execute"), e -> processExecute());
        executeButton.prefWidthProperty().bind(right.widthProperty());
        executeButton.setPrefHeight(Size.EX_BUTTON.getSize());
        right.getChildren().add(executeButton);
    }

    private void processExecute() {
        commandEntry.processCommands();
    }

    /**
     * returns the root containing the border pane and all view components
     *
     * @return Group containing all view components
     */
    @Override
    public Group getGroup() {
        return root;
    }

    /**
     * returns the group contained by the turtle area scroll pane
     *
     * @return turtle area group
     */
    @Override
    public Group getInnerGroup() {
        return turtleDisplay.getTurtleArea();
    }

    /**
     * returns all simplestring properties defined in the view that need to be
     * bound to their twins in the controller/model
     *
     * @return list of all needed simplestring properties
     */
    @Override
    public List<SimpleStringProperty> getProperties() {
        return Arrays.asList(error, image, variables, definedCommands, turtleIDs);
    }

    /**
     * gets the title of the view for the workspace tab
     *
     * @return string title of view
     */
    @Override
    public String getTitle() {
        return myResources.getString("WSTitle");
    }
}
