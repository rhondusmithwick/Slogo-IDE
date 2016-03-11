package view.tbar.popupdisplays.pen;

import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.ComboBox;
import maps.ColorMap;
import observables.ObjectObservable;
import view.tbar.popupdisplays.TurtlePropertyUpdater;
import view.utilities.ComboFactory;

public class PenColorUpdater extends TurtlePropertyUpdater {

	private ColorMap colorMap;
	private List<String> colors;
	private ComboBox<String> colorBox;
	
	public PenColorUpdater(SimpleStringProperty turtleIDs,
			ObjectObservable<String> internalCommand, ObjectObservable<String> parsingLanguage, ColorMap colorMap, List<String> colors){
		super(turtleIDs, internalCommand, parsingLanguage, "pColor");
		this.colorMap=colorMap;
		this.colors = colors;

	}
	
	
	@Override
	protected void createElementsBelowCheckBoxes() {
		colorBox = ComboFactory.createBox(getStringFromResources("pColor"), colors, null);
		addToScene(colorBox);
		
		
		

	}

	@Override
	protected String makeCommand(String turtleIDs) {
		String askCommand = translateCommand("Ask");
		String penColorCommand = translateCommand("SetPenColor");
		String pColor = colorBox.getSelectionModel().getSelectedItem();
		int index = colorMap.getIndex(pColor);
		return askCommand + " [ " + turtleIDs + "] [ " + penColorCommand + " " + Integer.toString(index) + " ]";
	}

}
