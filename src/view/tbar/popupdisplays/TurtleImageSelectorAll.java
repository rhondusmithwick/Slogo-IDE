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

public class TurtleImageSelectorAll extends PopUp implements Observer {

	private static final int POPUP_HEIGHT = 500;
	private static final int POPUP_WIDTH = 300;
	private static final String POPUP_BACKGROUND_COLOR = "-fx-background-color: white";
	private final ObservableMap<Integer, Turtle> allTurtles;
	private final ObjectObservable<String> internalCommand;
	private final List<PreviewUnitWithEditable> previewUnits;
	private final ObjectObservable<String> parsingLanguage; 
	private final IndexMap imageMap;

	public TurtleImageSelectorAll(ObservableMap<Integer, Turtle> turtles, ObjectObservable<String> internalCommand,
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

	private void generatePreviewUnits() {
		previewUnits.clear();
		for (IEditableElement turtle : allTurtles.values()) {
			PreviewUnitWithEditable previewUnit = new PreviewUnitWithEditable(turtle);
			previewUnit.addObserver(this);
			previewUnits.add(previewUnit);
		}
	}

	private List<Node> getPreviewUnitNodes() {
		List<Node> nodes = new ArrayList<Node>();
		previewUnits.forEach(unit -> nodes.add(unit.getHBox()));
		return nodes;
	}

	private void createAndDisplayPreviewUnits() {
		generatePreviewUnits();
		addNodes(getPreviewUnitNodes());
	}

	private void setTurtleImage(int turtleID, String newImageName) {
		int index = imageMap.getIndex(newImageName);
		String askCommand = translateCommand("Ask");
		String shapeCommand = translateCommand("SetShape");
		String fullCommand = askCommand + " [ " + turtleID + "] [ " + shapeCommand + " " + Integer.toString(index)
				+ " ]";
		internalCommand.set(fullCommand);
	}
	
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

	private String translateCommand(String command) {
		return GetCommand.makeCommand(command, parsingLanguage.get());
	}
	
	private int getTurtleID(Turtle turtle) {
		for (Integer ID : allTurtles.keySet()) {
			if (allTurtles.get(ID) == turtle) {
				return ID;
			}
		}
		return -1;
	}

}
