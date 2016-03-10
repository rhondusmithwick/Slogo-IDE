package view.tbar;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import view.Defaults;
import view.utilities.PopUp;

/**
 * This class provides a platform that allows users to select a turtle and make it active
 * 
 * @author Stephen Kwok
 *
 */

public class TurtleSelector extends PopUp {
	
	private List<Node> nodeList;
	private ResourceBundle myResources;

	public TurtleSelector(int height, int width, String backgroundColor) {
		super(height, width, backgroundColor);
		nodeList = new ArrayList<Node>();
        this.myResources = ResourceBundle.getBundle(Defaults.DISPLAY_LOC.getDefault());
	}

	@Override
	protected void createScene() {
		createTitle("turtleSelectTitle");
		createCheckBoxes();
		displayElements();
	}
	
	private void createCheckBoxes() {
		// Strings currently hard-coded
		createCheckBox("Option 1");
		createCheckBox("Option 2");
	}
	
	private void createCheckBox(String title) {
		CheckBox checkBox = new CheckBox(title);
		// add listeners
		nodeList.add(checkBox);
	}
	
	private void createTitle(String titleName) {
        Label title = new Label(myResources.getString(titleName));
        title.prefWidthProperty().bind(getSize(false));
        title.setAlignment(Pos.TOP_CENTER);
        nodeList.add(title);
    }
	
	private void displayElements() {
		addNodes(nodeList);
	}

}
