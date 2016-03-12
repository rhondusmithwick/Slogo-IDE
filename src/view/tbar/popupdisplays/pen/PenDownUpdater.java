package view.tbar.popupdisplays.pen;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import observables.ObjectObservable;

/**
 * Class Responsible for setting the pen down for all user selected turtles.
 * Extends the abstract PenUpDownUpdater class.
 * @author Stephen Kwok
 *
 */

public class PenDownUpdater extends PenUpDownUpdater {

	/**
	 * 
	 * @param turtleIDs SimpleStringProperty containing IDs of all created turtles
	 * @param internalCommand Observable that can be set to pass commands to back end
	 * @param parsingLanguage Observable containing current parsing language
	 */
	public PenDownUpdater(SimpleStringProperty turtleIDs,
			ObjectObservable<String> internalCommand, ObjectObservable<String> parsingLanguage) {
		super(turtleIDs, internalCommand, parsingLanguage, "penDownUpdaterTitle");
	}

	/**
	 * returns pen down command
	 */
	@Override
	protected String makeCommand(String turtleIDs) {
		return generatePenUpDownCommand(turtleIDs, "PenDown");
	}

	/**
	 * Creates label prompting user to click button to set pen down for selected turtles
	 */
	@Override
	protected void createElementsBelowCheckBoxes() {
		addToScene(new Label(getStringFromResources("penDownButtonPrompt")));
	}

}
