package view.tbar.popupdisplays.pen;

import javafx.beans.property.SimpleStringProperty;
import observables.ObjectObservable;
import view.tbar.popupdisplays.TurtlePropertyUpdater;


/**
 * This class extends the TurtlePropertyUpdater class and creates a pop-up that allows
 * the user to select multiple turtles and set their pens to up
 *
 * @author Stephen
 */
public abstract class PenUpDownUpdater extends TurtlePropertyUpdater {

    /**
     * @param turtleIDs       list (as String) of all IDs of turtles created
     * @param internalCommand observable that can be changed to pass commands to back end
     * @param parsingLanguage SimpleStringProperty containing current parsing language
     * @param titleText       String of the pop-up title
     */
    protected PenUpDownUpdater(SimpleStringProperty turtleIDs,
                            ObjectObservable<String> internalCommand, ObjectObservable<String> parsingLanguage, String titleText) {
        super(turtleIDs, internalCommand, parsingLanguage, titleText);
    }

    /**
     * takes String containing IDs of selected turtles and returns command
     * setting pens up/down for selected turtles
     */
    @Override
    protected abstract String makeCommand(String turtleIDs);

    /**
     * creates all GUI elements below the check boxes
     */
    @Override
    protected abstract void createElementsBelowCheckBoxes();

    /**
     * @param turtleIDs String containing IDs of all turtles selected
     * @param command   String indicating whether pen should be set up or down
     * @return String of command that sets pen up/down for all selected turtles
     */
    protected String generatePenUpDownCommand(String turtleIDs, String command) {
        String askCommand = translateCommand("Ask");
        String penCommand = translateCommand(command);
        return askCommand + " [ " + turtleIDs + "] [ " + penCommand + " ]";
    }

}
