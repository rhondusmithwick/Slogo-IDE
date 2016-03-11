package view.tbar.popupdisplays;

import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.ComboBox;
import observables.ObjectObservable;
import view.Defaults;
import view.Size;
import view.utilities.ComboFactory;
import view.utilities.PopUp;

public class TurtlePropSelect extends PopUp {
	
	private ResourceBundle myResources;
	private ObjectObservable<Integer> selectedTurtle;
	private SimpleStringProperty turtleIds;
	private ComboBox<String> turtleSelect;

	public TurtlePropSelect(ObjectObservable<Integer> selectedTurtle, SimpleStringProperty turtleIds){
		super(Size.TURTLE_UPDATE_POPUP_HEIGHT.getSize(), Size.TURTLE_UPDATE_POPUP_WIDTH.getSize(), 
				Defaults.BACKGROUND_COLOR.getDefault());
		this.myResources = ResourceBundle.getBundle(Defaults.DISPLAY_LOC.getDefault());
	}
	
	@Override
	protected void createScene() {
		turtleSelect = ComboFactory.createBox(myResources.getString("turtleParamSel"), 
				Arrays.asList(turtleIds.get().split(",")), e->updateSelected());
		addNodes(Arrays.asList(turtleSelect));

	}

	private void updateSelected() {
		selectedTurtle.set(Integer.parseInt(turtleSelect.getSelectionModel().getSelectedItem()));
	}

}
