package view.tbar.popupdisplays;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import observables.ObjectObservable;

public class PenUpUpdater extends PenUpDownUpdater {

	public PenUpUpdater(int height, int width, String backgroundColor, SimpleStringProperty turtleIDs,
			ObjectObservable<String> internalCommand, ObjectObservable<String> parsingLanguage) {
		super(height, width, backgroundColor, turtleIDs, internalCommand, parsingLanguage);
		setTitle("penUpUpdaterTitle");
	}

	@Override
	protected String makeCommand(String turtleIDs) {
		return generatePenUpDownCommand(turtleIDs, "PenUp");
	}
	
	@Override
	protected void createElementsBelowCheckBoxes() {
		addToScene(new Label(getStringFromResources("penUpButtonPrompt")));
	}

}
