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
import view.utilities.ButtonFactory;
import view.utilities.PopUp;

/**
 * This class provides a platform that allows users to select a turtle and make
 * it active
 * 
 * @author Stephen Kwok
 *
 */

public class TurtleSelector extends PopUp {

	private final List<Node> nodeList;
	private final List<CheckBox> checkBoxList;
	private final ResourceBundle myResources;
	private final SimpleStringProperty turtleIDs;
	private final Button activateTurtlesButton;
	private ObjectObservable<String> intCommand;

	public TurtleSelector(int height, int width, String backgroundColor, SimpleStringProperty turtleIDs,
			ObjectObservable<String> intCommand) {
		super(height, width, backgroundColor);
		this.myResources = ResourceBundle.getBundle(Defaults.DISPLAY_LOC.getDefault());
		this.intCommand = intCommand;
		this.turtleIDs = turtleIDs;
		nodeList = new ArrayList<>();
		checkBoxList = new ArrayList<>();
		activateTurtlesButton = ButtonFactory.createButton(myResources.getString("activateTurtles"),
				e -> activateTurtles());
	}

	@Override
	protected void createScene() {
		createTitle(myResources.getString("turtleSelectTitle"));
		createCheckBoxes();
		addButtonToDisplay();
		displayElements();
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

	private void displayElements() {
		addNodes(nodeList);
	}
	
	private String generateTellCommand(String turtleIDs) {
		return String.format(Defaults.ACTIVATE_TURTLES_COMMAND.getDefault(), turtleIDs);
	}
	
	private void activateSelectedTurtles(String turtleIDs) {
		String command = generateTellCommand(turtleIDs);
		intCommand.set(command);
	}

}
