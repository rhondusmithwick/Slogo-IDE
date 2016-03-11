package view.tbar.popupdisplays;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import observables.ObjectObservable;
import view.Defaults;
import view.Size;
import view.utilities.ButtonFactory;
import view.utilities.PopUp;

/**
 * This class provides a platform that allows users to select a turtle and make
 * it active. It is a subclass of the abstract popup class
 * 
 * @author Stephen Kwok
 *
 */

public class TurtleSelector extends PopUp {

	private final List<Node> nodeList;
	private final List<CheckBox> checkBoxList;
	private final ResourceBundle myResources;
	private final SimpleStringProperty turtleIDs;
	private Button activateTurtlesButton;
	private ObjectObservable<String> intCommand;

	/**
	 * Creates a new turtle selector object
	 * @param turtleIDs list of all current turtle IDS	
	 * @param intCommand Object observable string to pass commands to the command entry instance
	 */
	public TurtleSelector(SimpleStringProperty turtleIDs, ObjectObservable<String> intCommand) {
		super(Size.TURT_SELECT_HEIGHT.getSize(), Size.TURT_SELECT_WIDTH.getSize(), Defaults.BACKGROUND_COLOR.getDefault());
		this.myResources = ResourceBundle.getBundle(Defaults.DISPLAY_LOC.getDefault());
		this.intCommand = intCommand;
		this.turtleIDs = turtleIDs;
		nodeList = new ArrayList<>();
		checkBoxList = new ArrayList<>();
		
	}

	/**
	 * creates the graphical scene for the turtle selector by setting up the title,
	 * checkboxes, and the button to set the active turtles
	 */
	@Override
	protected void createScene() {
		createTitle(myResources.getString("turtleSelectTitle"));
		createCheckBoxes();
		addButtonToDisplay();
		addNodes(nodeList);
	}

	private void createTitle(String titleName) {
		Label title = new Label(titleName);
		title.prefWidthProperty().bind(getSize(false));
		title.setAlignment(Pos.TOP_CENTER);
		nodeList.add(title);
	}

	private void createCheckBoxes() {
		String turtleIDString = turtleIDs.get();
		turtleIDString = turtleIDString.substring(1, turtleIDString.length() - 1);
		String[] turtleIDS = turtleIDString.split(",");
		for (String ID : turtleIDS) {
			createCheckBox(ID.trim());
		}
	}

	private void createCheckBox(String title) {
		CheckBox checkBox = new CheckBox(title);
		checkBoxList.add(checkBox);
		nodeList.add(checkBox);
	}

	private void addButtonToDisplay() {
		activateTurtlesButton = ButtonFactory.createButton(myResources.getString("activateTurtles"),
				e -> activateTurtles());
		nodeList.add(activateTurtlesButton);
	}

	private void activateTurtles() {
		StringBuilder activeTurtles = new StringBuilder();
		for (CheckBox checkBox : checkBoxList) {
			if (checkBox.isSelected()) {
				activeTurtles.append(checkBox.getText() + " ");
			} else {
				// hide inactive turtle
			}
		}
		activateSelectedTurtles(activeTurtles.toString());
		closeScene();
	}
	
	
	private void activateSelectedTurtles(String turtleIDs) {
		String command = String.format(Defaults.ACTIVATE_TURTLES_COMMAND.getDefault(), turtleIDs);
		intCommand.set(command);
	}

}
