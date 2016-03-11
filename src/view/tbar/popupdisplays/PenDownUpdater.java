package view.tbar.popupdisplays;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import observables.ObjectObservable;

public class PenDownUpdater extends PenUpDownUpdater {

	public PenDownUpdater(int height, int width, String backgroundColor, SimpleStringProperty turtleIDs,
			ObjectObservable<String> internalCommand, ObjectObservable<String> parsingLanguage) {
		super(height, width, backgroundColor, turtleIDs, internalCommand, parsingLanguage);
		setTitle("penDownUpdaterTitle");
	}

	@Override
	protected String makeCommand(String turtleIDs) {
		return generatePenUpDownCommand(turtleIDs, "PenDown");
	}

	@Override
	protected void createElementsBelowCheckBoxes() {
		addToScene(new Label(getStringFromResources("penDownButtonPrompt")));
	}

}
