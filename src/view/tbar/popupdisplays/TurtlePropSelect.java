package view.tbar.popupdisplays;

import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
		super(Size.TURTLE_UPDATE_POPUP_WIDTH.getSize(), Size.PROP_SEL_HEIGHT.getSize(), 
				Defaults.BACKGROUND_COLOR.getDefault());
		this.myResources = ResourceBundle.getBundle(Defaults.DISPLAY_LOC.getDefault());
		this.selectedTurtle=selectedTurtle;
		this.turtleIds=turtleIds;
	}
	
	@Override
	protected void createScene() {
		String IDS = turtleIds.get().substring(1, turtleIds.get().length()-1);
		Label title = new Label(myResources.getString("turtleParamSel"));
		turtleSelect = ComboFactory.createBox(myResources.getString("turtleParamSel"), 
				Arrays.asList(IDS.split(", ")), e->updateSelected());
		addNodes(Arrays.asList(title, turtleSelect));

	}

	private void updateSelected() {
		selectedTurtle.set(Integer.parseInt(turtleSelect.getSelectionModel().getSelectedItem()));
		closeScene();
	}

}
