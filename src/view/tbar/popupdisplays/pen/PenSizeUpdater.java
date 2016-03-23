// This entire file is part of my masterpiece.
// Stephen Kwok

// This class generates a pop up that allows the user to select multiple turtles and update those turtles'
// pen size

// This class was included in the masterpiece to highlight the benefits of creating the TurtlePropertyUpdater abstract class
// As seen below, because many features common to all subclasess are already in the abstract class, the resulting subclasses
// are very short and primarily only differ in what command is generated to pass to the back end in order to update
// a certain property. This helps each subclass adhere to the Single Responsibility Principle since each subclass need only
// generate a property-specific command. Everything else is handled by the abstract class. 

package view.tbar.popupdisplays.pen;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import observables.ObjectObservable;
import view.tbar.popupdisplays.TurtlePropertyUpdater;

/**
 * Class responsible for creating popup that allows user to select certain turtles
 * and change their pen sizes to a given value
 *
 * @author Stephen
 */

public class PenSizeUpdater extends TurtlePropertyUpdater {

    private TextField userInput;

    /**
     * creates new PenSizeUpdater instance
     *
     * @param language   string observable for setting and getting parsing language
     * @param intCommand string observable to pass commands to command entry instance to pass to backend
     */

    public PenSizeUpdater(SimpleStringProperty turtleIDs,
                          ObjectObservable<String> internalCommand, ObjectObservable<String> parsingLanguage) {
        super(turtleIDs, internalCommand, parsingLanguage, "penSizeUpdateTitle");
    }

    /**
     * Creates text field to allow user to input new pen size for selected turtles
     */
    @Override
    protected void createElementsBelowCheckBoxes() {
        userInput = new TextField();
        userInput.prefWidthProperty().bind(getSize(false));
        VBox.setVgrow(userInput, Priority.ALWAYS);
        addToScene(new Label(getStringFromResources("penSizeInputPrompt")));
        addToScene(userInput);
    }

    /**
     * Generates command to update pen sizes to size entered by user for all selected
     * turtles
     */
    @Override
    protected String makeCommand(String turtleIDs) {
        String askCommand = translateCommand("Ask");
        String penSizeCommand = translateCommand("SetPenSize");
        String newPenSize = userInput.getText().trim();
        return askCommand + " [ " + turtleIDs + "] [ " + penSizeCommand + " " + newPenSize + " ]";
    }

}
