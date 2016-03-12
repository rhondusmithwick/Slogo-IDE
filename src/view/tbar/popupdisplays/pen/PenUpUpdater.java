package view.tbar.popupdisplays.pen;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import observables.ObjectObservable;

/**
 * This class creates a pop-up with check boxes for each created turtle. The user can check boxes
 * to select multiple turtles, and when the "Apply Changes" button is clicked, all selected turtles
 * have their pens set up
 *
 * @author Stephen
 */

public class PenUpUpdater extends PenUpDownUpdater {

    /**
     * Creates instance of PenUpUpdater
     *
     * @param turtleIDs       SimpleStringProperty containing String of all turtles created
     * @param internalCommand ObjectObservable that can be set to pass commands from front to back end
     * @param parsingLanguage ObjectObservable with current parsing language
     */
    public PenUpUpdater(SimpleStringProperty turtleIDs,
                        ObjectObservable<String> internalCommand, ObjectObservable<String> parsingLanguage) {
        super(turtleIDs, internalCommand, parsingLanguage, "penUpUpdaterTitle");

    }

    /**
     * returns pen up command
     */
    @Override
    protected String makeCommand(String turtleIDs) {
        return generatePenUpDownCommand(turtleIDs, "PenUp");
    }

    /**
     * Adds label to scene prompting user to set pen up for selected turtles by pressing button
     */
    @Override
    protected void createElementsBelowCheckBoxes() {
        addToScene(new Label(getStringFromResources("penUpButtonPrompt")));
    }

}
