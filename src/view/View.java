package view;

import main.GlobalProperties;
import view.commentry.CommandEntry;
import view.commhistory.CommandHistoryDisplay;
import view.envdisplay.DefinedObjectsDisplay;
import view.envdisplay.MethodDisplay;
import view.envdisplay.VariableDisplay;
import view.error.ErrorDisplay;
import view.tbar.BottomBar;
import view.tbar.SubBar;
import view.tbar.TopBar;
import view.turtdisplay.TurtleDisplay;
import view.turtparams.TurtleParams;
import view.utilities.ButtonFactory;
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
import maps.ColorMap;
import maps.ImageMap;
import maps.IndexMap;
import observables.ObjectObservable;
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
	private final ObjectObservable<String> parsingLanguage, input, backgroundColor, internalCommand, commandHistInput;
	private final SimpleStringProperty image = new SimpleStringProperty(this, "turtleImage");
	private final SimpleStringProperty penColor = new SimpleStringProperty(this, "penColor");
	private final SimpleStringProperty variables = new SimpleStringProperty(this, "variables");
	private final SimpleStringProperty definedCommands = new SimpleStringProperty(this, "definedCommands");
	private final SimpleStringProperty error = new SimpleStringProperty(this, "error");
	private final SimpleObjectProperty<Point2D> location = new SimpleObjectProperty<>(this, "location");
	private final SimpleDoubleProperty heading = new SimpleDoubleProperty(this, "heading");
	private final SimpleBooleanProperty penDown = new SimpleBooleanProperty(this, "penDown");
	private final SimpleStringProperty turtleIDs = new SimpleStringProperty(this, "turtleIDs");
    private final IndexMap cMap, iMap;
	private BorderPane UI;
	private ResourceBundle myResources;
	private Group root;
	private Button executeButton;
	private TurtleDisplay turtleDisplay;
	private CommandHistoryDisplay commandHistory;
	private CommandEntry commandEntry;
	private ErrorDisplay errorDisplay;
	private DefinedObjectsDisplay variableDisplay, methodsDisplay;
	private TurtleParams turtleParameters;
	private HBox bottom;
	private VBox left, right, top;
	private SubBar topBar, bottomBar;

	/**
	 * creates a new view object
	 * 
	 * @param turtleDispDimension
	 *            2D dimension defining the turtle area size
	 * @param input
	 *            observable string used to pass input to back end
	 * @param pLang
	 *            observable string used to set parsing language on backend
	 */
	public View(GlobalProperties globalProperties, Dimension2D turtleDispDimension) {
		this.parsingLanguage = globalProperties.getLanguage();
		this.input = globalProperties.getInput();
        this.iMap = globalProperties.getImageMap();
        this.cMap = globalProperties.getColorMap();
		this.turtleDispDimension = turtleDispDimension;
		this.internalCommand = new ObjectObservable<>();
		this.backgroundColor = globalProperties.getBackgroundColor();
		this.commandHistInput = new ObjectObservable<>();
		this.myResources = ResourceBundle.getBundle(Defaults.DISPLAY_LOC.getDefault());
		createAppview();
		//setPreferences();

	}

	private void setPreferences() {
		PreferenceSetter pSet = new PreferenceSetter(parsingLanguage, cMap, iMap, backgroundColor, internalCommand);
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
	 * @param scene
	 *            to bind the panes size to
	 */
	@Override
	public void bindSize(Scene scene) {
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
		UI.setCenter(turtleDisplay.getTurtlePane());
		UI.setRight(right);
		UI.setLeft(left);
		UI.setBottom(bottom);
		UI.setTop(top);
	}

	private void createLeftPane() {
		left = new VBox(Size.VIEW_PADDING.getSize());
		BorderPane.setMargin(left, ViewInsets.LEFT.getInset());
		variableDisplay = new VariableDisplay(parsingLanguage, internalCommand, variables, error);
		left.getChildren().add(variableDisplay.getEnvDisplay());
		methodsDisplay = new MethodDisplay(parsingLanguage, internalCommand, definedCommands, error);
		left.getChildren().add(methodsDisplay.getEnvDisplay());

	}

	private void createBottomPane() {
		bottom = new HBox(Size.VIEW_PADDING.getSize());
		BorderPane.setMargin(bottom, ViewInsets.BOTTOM.getInset());

		turtleParameters = new TurtleParams(location, heading, penDown, penColor);
		bottom.getChildren().add(turtleParameters.getTurtleParams());

		commandHistory = new CommandHistoryDisplay(internalCommand, commandHistInput);
		bottom.getChildren().add(commandHistory.getHistoryGraphic());

	}


	private void createToolBar() {
		top = new VBox(Size.TB_PADDING.getSize());
		topBar = new TopBar(parsingLanguage, backgroundColor, image, turtleIDs, internalCommand, (ColorMap) cMap, (ImageMap) iMap);
		bottomBar = new BottomBar(parsingLanguage, internalCommand, (ColorMap) cMap, (ImageMap) iMap);
		top.getChildren().addAll(topBar.getContainer(), bottomBar.getContainer());
		BorderPane.setMargin(top, ViewInsets.TOP.getInset());

	}

	private void createTurtleDisplay() {

		turtleDisplay = new TurtleDisplay(backgroundColor, turtleDispDimension);
		BorderPane.setMargin(turtleDisplay.getTurtlePane(), ViewInsets.TURTLE.getInset());

	}

	private void createRightPane() {
		right = new VBox();
		BorderPane.setMargin(right, ViewInsets.RIGHT.getInset());
		commandEntry = new CommandEntry(input, internalCommand, commandHistInput);
		right.getChildren().add(commandEntry.getNode());
		createExecute();
	}

	private void createExecute() {
		executeButton = ButtonFactory.createButton(myResources.getString("execute"), e -> processExecute());
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
		return Arrays.asList(error, image, penColor, variables, definedCommands, turtleIDs);
	}

	
	public ObjectObservable<String> getBackgroundColor() {
		return backgroundColor;
	}
}
