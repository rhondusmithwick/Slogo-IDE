package view.tbar.popupdisplays;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javafx.collections.ObservableMap;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import maps.IndexMap;
import model.turtle.Turtle;
import observables.ObjectObservable;
import view.Defaults;
import view.utilities.GetCommand;
import view.utilities.PopUp;
import view.utilities.PreviewUnitWithEditable;

/**
 * Creates a pop up with preview units display each created turtle's image and name.
 * On click, the preview units open an ImageChooser allowing the user to change
 * the selected turtle's image
 * 
 * @author Stephen
 *
 */

public class TurtleImageSelector extends PopUp implements Observer {

	private static final int POPUP_HEIGHT = 500;
	private static final int POPUP_WIDTH = 300;
	private static final String POPUP_BACKGROUND_COLOR = "-fx-background-color: white";
	private static final String ASK_COMMAND_ENGLISH = "Ask";
	private static final String SHAPE_COMMAND_ENGLISH = "SetShape";
	private final ObservableMap<Integer, Turtle> allTurtles;
	private final ObjectObservable<String> internalCommand;
	private final List<PreviewUnitWithEditable> previewUnits;
	private final ObjectObservable<String> parsingLanguage;
	private final IndexMap imageMap;

	/**
	 * Creates an instance of TurtleImageSelector
	 * @param turtles: a map containing all created turtles and their IDs
	 * @param internalCommand: the command to be passed to the back end
	 * @param parsingLanguage: the current parsing language
	 * @param imageMap: map of images
	 */
	public TurtleImageSelector(ObservableMap<Integer, Turtle> turtles, ObjectObservable<String> internalCommand,
			ObjectObservable<String> parsingLanguage, IndexMap imageMap) {
		super(POPUP_WIDTH, POPUP_HEIGHT, POPUP_BACKGROUND_COLOR);
		this.allTurtles = turtles;
		this.internalCommand = internalCommand;
		this.parsingLanguage = parsingLanguage;
		this.imageMap = imageMap;
		this.previewUnits = new ArrayList<PreviewUnitWithEditable>();
	}

	@Override
	protected void createScene() {
		createAndDisplayPreviewUnits();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		setTurtleImage(getTurtleID((Turtle) arg1), promptUserForImage());
		closeScene();
	}

	/**
	 * Creates the preview units displaying each created turtle and its name.
	 */
	private void generatePreviewUnits() {
		previewUnits.clear();
		for (Integer ID : allTurtles.keySet()) {
			IEditableElement turtle = allTurtles.get(ID);
			turtle.setName(String.valueOf(ID));
			PreviewUnitWithEditable previewUnit = new PreviewUnitWithEditable(turtle);
			previewUnit.addObserver(this);
			previewUnits.add(previewUnit);
		}
	}

	/**
	 * Creates list of nodes from the list of preview units
	 * @return: a list of the nodes containing each preview unit
	 */
	private List<Node> getPreviewUnitNodes() {
		List<Node> nodes = new ArrayList<Node>();
		previewUnits.forEach(unit -> nodes.add(unit.getHBox()));
		return nodes;
	}

	/**
	 * Creates the preview units and displays them in the pop up
	 */
	private void createAndDisplayPreviewUnits() {
		generatePreviewUnits();
		previewUnits.forEach(unit -> unit.update());
		addNodes(getPreviewUnitNodes());
	}

	/**
	 * Sets a turtle's image
	 * @param turtleID: the ID of the turtle whose image is to be set
	 * @param newImageName: the name of the image the turtle's image should be set to
	 */
	private void setTurtleImage(int turtleID, String newImageName) {
		int index = imageMap.getIndex(newImageName);
		String askCommand = translateCommand(ASK_COMMAND_ENGLISH);
		String shapeCommand = translateCommand(SHAPE_COMMAND_ENGLISH);
		String fullCommand = askCommand + " [ " + turtleID + " ] [ " + shapeCommand + " " + Integer.toString(index)
				+ " ]";
		internalCommand.set(fullCommand);
	}

	/**
	 * Opens ImageChooser to allow user to pick new image for turtle
	 * @return name of the image the turtle's image is to be set to
	 */
	private String promptUserForImage() {
		ImageChooser imageChooser = new ImageChooser();
		imageChooser.show();
		try {
			String imageName = imageChooser.getChosen();
			int index = imageName.indexOf(Defaults.IMAGE_LOC.getDefault());
			return imageName.substring(index);
		} catch (MalformedURLException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText(e.getClass().toString());
			alert.show();
			return null;
		}
	}

	/**
	 * Translates commands from English to current parsing language
	 * @param command: command in English
	 * @return command in current parsing language
	 */
	private String translateCommand(String command) {
		return GetCommand.makeCommand(command, parsingLanguage.get());
	}

	/**
	 * Gets the ID of a given turtle
	 * @param turtle: turtle whose ID is to be returned
	 * @return: ID of the given turtle
	 */
	private int getTurtleID(Turtle turtle) {
		for (Integer ID : allTurtles.keySet()) {
			if (allTurtles.get(ID) == turtle) {
				return ID;
			}
		}
		return -1;
	}

}
